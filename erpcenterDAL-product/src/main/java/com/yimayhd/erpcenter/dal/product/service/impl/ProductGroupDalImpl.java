package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.zookeeper.server.FinalRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupPriceMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupSellerMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupSupplierMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSeller;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class ProductGroupDalImpl implements ProductGroupDal {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductGroupDalImpl.class);
	@Autowired
	private ProductGroupMapper productGroupMapper;
	@Autowired
	private ProductGroupPriceMapper priceMapper;
	@Autowired
	private ProductGroupSupplierMapper groupSupplierMapper;
	@Autowired
	private ProductGroupSellerMapper sellerMapper;
	@Autowired
	private TransactionTemplate transactionTemplateProduct;
	//@Transactional
	@Override
	public int save(final ProductGroup productGroup) {
		final Integer groupId = productGroup.getId();
		final List<ProductGroupSeller> selectGroupSellerList = sellerMapper.selectGroupSellerList(null, groupId, null);
		final List<ProductGroupPrice> selectPriceResult = priceMapper.selectPricesByGroupId2(groupId);
		int dbResult = transactionTemplateProduct.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				int i = 0;
				try {
					if(productGroup.getId()!=null){			
						i = productGroupMapper.updateByPrimaryKeySelective(productGroup);
						if (i <= 0) {
							status.setRollbackOnly();
							LOGGER.error("productGroupMapper.updateByPrimaryKeySelective error,params:productGroup={},result:{}",JSON.toJSONString(productGroup),i);
							return 0;
						}
						//删除价格组
						if(productGroup.getState()!=null && productGroup.getState()==-1){
							//删除价格组的用户列表和价格表
							//afterDelAgencyGroup(productGroup.getId());
							
							int deleteSellerResult = sellerMapper.deleteByGroupId(groupId);
							if (deleteSellerResult != selectGroupSellerList.size()) {
								status.setRollbackOnly();
								LOGGER.error("sellerMapper.deleteByGroupId error,params:groupId={},result:{}",groupId,deleteSellerResult);
								return 0;
							}
							List<Integer> groupIdList = new ArrayList<Integer>();
							groupIdList.add(groupId);
							int deletePriceResult = priceMapper.deleteByGroupIdList(groupIdList);
							if (deletePriceResult != selectPriceResult.size()) {
								status.setRollbackOnly();
								LOGGER.error("priceMapper.deleteByGroupIdList error,params:groupId={},result:{}",groupId,deletePriceResult);
								return 0;
							}
						}
					}else{
						productGroup.setState((byte)1);
						productGroup.setCreateTime(System.currentTimeMillis());
						i = productGroupMapper.insertSelective(productGroup);
						if (i <= 0) {
							status.setRollbackOnly();
							LOGGER.error("productGroupMapper.insertSelective error,params:productGroup={},result:{}",JSON.toJSONString(productGroup),i);
							return 0;
						}
					}
					return i;
				} catch (Exception e) {
					status.setRollbackOnly();
					LOGGER.error("error:{}",e);
					return 0;
				}
			}
		});
		if (dbResult <= 0) {
			LOGGER.error("");
		}
		return dbResult;
	}
	
	@Override
	public List<ProductGroup> selectProductGroups(Integer productId) {
		
		return productGroupMapper.selectProductGroups(productId);
	}
	@Override
	public int validateName(String name, Integer productId,Integer id) {
		
		return productGroupMapper.validateName(name,productId,id);
	}
	@Override
	public ProductGroup getGroupInfoById(Integer groupId) {		
		return productGroupMapper.selectByPrimaryKey(groupId);
	}
	@Override
	public List<ProductGroup> selectProductGroupList(Integer groupSupplierId) {
		return productGroupMapper.selectProductGroupList(groupSupplierId);
	}
	//@Transactional
	@Override
	public void save2(final List<ProductGroup> productGroups,final Integer groupSupplierId) {
		//int id=0;
		//if (productGroups!=null && productGroups.size()>0) {
		final List<ProductGroup> productGroupsList = productGroupMapper.getIdListByProductSupplierId(groupSupplierId);
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					int updateStateBySupplierId = productGroupMapper.updateStateBySupplierId(groupSupplierId);
					if (updateStateBySupplierId != productGroupsList.size()) {
						status.setRollbackOnly();
						LOGGER.error("productGroupMapper.updateStateBySupplierId ,params:groupSupplierId={},result:{}",groupSupplierId,updateStateBySupplierId);
						return false;
					}
					//}
					//List<Integer> idList=new ArrayList<Integer>();
					for (ProductGroup pGroup : productGroups) {
						
						if(pGroup.getId()!=null){
							List<ProductGroupPrice> selectPricesByGroupId2 = priceMapper.selectPricesByGroupId2(pGroup.getId());
							pGroup.setState((byte)1);
							int updateByGroupId = priceMapper.updateByGroupId(pGroup.getId());
							if (updateByGroupId != selectPricesByGroupId2.size()) {
								status.setRollbackOnly();
								LOGGER.error("priceMapper.updateByGroupId ,params:groupId={},result:{}",pGroup.getId(),updateByGroupId);
								return false;
							}
							int updateByPrimaryKeySelective = productGroupMapper.updateByPrimaryKeySelective(pGroup);
							if (updateByPrimaryKeySelective < 1) {
								status.setRollbackOnly();
								LOGGER.error("productGroupMapper.updateByPrimaryKeySelective,params:ProductGroup={},result:{}",JSON.toJSONString(pGroup),updateByPrimaryKeySelective);
								return false;
							}
							for (ProductGroupPrice gPrice : pGroup.getGroupPrices()) {
								gPrice.setGroupId(pGroup.getId());
								gPrice.setState((byte)1);
								gPrice.setCreateTime(System.currentTimeMillis());
								if (gPrice.getId()!=null) {
									int updateByPrimaryKeySelective2 = priceMapper.updateByPrimaryKeySelective(gPrice);
									if (updateByPrimaryKeySelective2 < 1) {
										status.setRollbackOnly();
										LOGGER.error("priceMapper.updateByPrimaryKeySelective , params:ProductGroupPrice={},result:{}",JSON.toJSONString(gPrice),updateByPrimaryKeySelective2);
										return false;
									}
								}
								else {
									gPrice.setCreateTime(System.currentTimeMillis());
									int insertSelective = priceMapper.insertSelective(gPrice);
									if (insertSelective < 1) {
										status.setRollbackOnly();
										LOGGER.error("priceMapper.insertSelective ,params:ProductGroupPrice={},result:{}",JSON.toJSONString(gPrice),insertSelective);
										return false;
									}
								}
							}
							
						}else{
							pGroup.setState((byte)1);
							pGroup.setCreateTime(System.currentTimeMillis());
							int insertSelective = productGroupMapper.insertSelective(pGroup);
							for (ProductGroupPrice gPrice : pGroup.getGroupPrices()) {
								gPrice.setGroupId(pGroup.getId());
								gPrice.setState((byte)1);
//					if (gPrice.getId()!=null) {
//						gPrice.setState((byte)1);
//						priceMapper.updateByPrimaryKeySelective(gPrice);
//					}
								gPrice.setCreateTime(System.currentTimeMillis());
								int insertSelective2 = priceMapper.insertSelective(gPrice);
								if (insertSelective2 < 1) {
									status.setRollbackOnly();
									LOGGER.error("priceMapper.insertSelective ,params:ProductGroupPrice={},result:{}",JSON.toJSONString(gPrice),insertSelective2);
									return false;
								}
							}
						}
					}
					int updateTimeById = groupSupplierMapper.updateTimeById(groupSupplierId);
					if (updateTimeById < 1) {
						status.setRollbackOnly();
						LOGGER.error("groupSupplierMapper.updateTimeById ,params:groupSupplierId={},result:{}",groupSupplierId,updateTimeById);
						return false;
					}
					return true;
				} catch (Exception e) {
					status.setRollbackOnly();
					LOGGER.error("error：{}",e);
					return false;
				}
			}
		});
		//return productGroup.getId();
	}

	@Override
	public int save3(ProductGroup productGroup) {
		productGroupMapper.insertSelective(productGroup);
		return productGroup.getId();
	}

	@Override
	public List<ProductGroupVo> selectGroupsByProdctIdAndSupplierId(
			Integer productId, Integer supplierId, Date groupDate) {
		return  productGroupMapper.selectGroupsByProductIdAndSupplierIdAndDate(supplierId, productId,groupDate);
	}

	//@Transactional
	@Override
	public void copyGroups(final List<Integer> groupIdList,final List<Integer> groupSupplierIdList) {	
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					for(Integer groupId : groupIdList){
						List<ProductGroupPrice> selectPriceResult = priceMapper.selectPricesByGroupId2(groupId);
						ProductGroup groupInfo = productGroupMapper.selectByPrimaryKey(groupId);
						groupInfo.setId(null);
						groupInfo.setState((byte)1);			
						for(Integer groupSupplierId : groupSupplierIdList){		
							groupInfo.setGroupSupplierId(groupSupplierId);
							int insertResult = productGroupMapper.insertSelective(groupInfo);
							if (insertResult < 1) {
								status.setRollbackOnly();
								LOGGER.error("productGroupMapper.insertSelective error,params:groupInfo={},result:{}",JSON.toJSONString(groupInfo),insertResult);
								return false;
							}
							int copyResult = priceMapper.copyPrices(groupId, groupInfo.getId());	
							if (copyResult != selectPriceResult.size()) {
								status.setRollbackOnly();
								LOGGER.error("productGroupMapper.insertSelective error,params:groupId={},productGroupId={},result:{}",groupId,groupInfo.getId(),insertResult);
								return false;
							}
						}			
					}
					return true;
				} catch (Exception e) {
					status.setRollbackOnly();
					LOGGER.error("error:{}",e);
					return false;
					
				}
			}
		});
	}

	@Override
	public List<ProductGroup> selectProductGroupsBySellerId(Integer productId,
			Integer sellerId) {		
		return productGroupMapper.selectProductGroupsBySellerId(productId, sellerId);
	}

	@Override
	public void afterDelAgencyGroup(Integer groupId) {
		sellerMapper.deleteByGroupId(groupId);
		List<Integer> groupIdList = new ArrayList<Integer>();
		groupIdList.add(groupId);
		priceMapper.deleteByGroupIdList(groupIdList);
	}

	
	
	

}

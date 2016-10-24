
package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupPriceMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupSupplierMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

public class ProductGroupSupplierDalImpl implements ProductGroupSupplierDal {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductGroupSupplierDalImpl.class);
	@Autowired
	private ProductGroupSupplierMapper groupSupplierMapper;
	@Autowired
	private ProductGroupMapper groupMapper;
	@Autowired
	private ProductGroupPriceMapper groupPriceMapper;
	@Autowired
	private TransactionTemplate transactionTemplateProduct;
	//@Transactional
	@Override
	public int save(final List<ProductGroupSupplier> groupSuppliers) {
		Integer dbResult = transactionTemplateProduct.execute(new TransactionCallback<Integer>() {

			int i =0;
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					for (ProductGroupSupplier productGroupSupplier : groupSuppliers) {
						productGroupSupplier.setState((byte)1);
						productGroupSupplier.setCreateTime(System.currentTimeMillis());
						i = groupSupplierMapper.insertSelective(productGroupSupplier);
						if (i < 1) {
							status.setRollbackOnly();
							LOGGER.error("groupSupplierMapper.insertSelective,params:productGroupSupplier={}",JSON.toJSONString(productGroupSupplier));
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
		if (dbResult == null || dbResult < 1) {
			LOGGER.error("");
			return 0;
		}
		return dbResult;
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers(
			Integer groupId) {
		
		return groupSupplierMapper.selectProductGroupSuppliers(groupId);
	}

	@Override
	public List<ProductGroupSupplierVo> selectProductGroupSupplierVos(Integer groupId, Integer priceId) {
		return groupSupplierMapper.selectProductGroupSupplierVos(groupId, priceId);
	}

	@Override
	public PageBean<ProductGroupSupplier> selectProductGroupSuppliersPage(PageBean<ProductGroupSupplier> pageBean, Integer groupId) {
		List<ProductGroupSupplier> productGroupSuppliers = groupSupplierMapper.selectProductGroupSupplierListPage(pageBean, groupId);
		pageBean.setResult(productGroupSuppliers);
		return pageBean;
	}

	@Override
	public int update(ProductGroupSupplier groupSupplier) {
		
		return groupSupplierMapper.updateByPrimaryKeySelective(groupSupplier);
	}

	@Override
	public ProductGroupSupplierVo selectProductGroupSupplierVosToSales(
			Integer groupId, Integer priceId, Integer supplierId) {
		return groupSupplierMapper.selectProductGroupSupplierVosToSales(groupId, priceId, supplierId);
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers2(
			Integer productId) {
		return groupSupplierMapper.selectProductGroupSupplierList(productId);
	}
	
	

	@Override
	public ProductGroupSupplier selectgGroupSupplierById(Integer id) {
		return groupSupplierMapper.selectById(id);
	}

	@Override
	public int save(final List<ProductInfo> productInfos, Integer productId) {		
		final Map parameters=new HashMap();
		parameters.put("fromProductId", productId);
		Integer dbResult = transactionTemplateProduct.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					for (ProductInfo pInfo:productInfos) {
						parameters.put("toProductId", pInfo.getId());
						Integer copyProduct = groupSupplierMapper.copyProduct(parameters);
					}
				} catch (Exception e) {
					status.setRollbackOnly();
					LOGGER.error("error:{}",e);
					return 0;
				}
				return 1;
			}
		});
		if (dbResult == null || dbResult < 1) {
			LOGGER.error("");
			return 0;
		}
		return dbResult;
	}

	@Override
	public List<ProductGroupSupplier> selectSupplierList(
			ProductSupplierCondition condition) {		
		return groupSupplierMapper.selectSupplierList(condition);
	}

	//@Transactional
	@Override
	public int deleteByProductSupplierId(final Integer productSupplierId) {
		final List<ProductGroup> groupList = groupMapper.getIdListByProductSupplierId(productSupplierId);
		Integer dbResult = transactionTemplateProduct.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					if(groupList!=null && groupList.size()>0){
						List<Integer> list = new ArrayList<Integer>();
						for(ProductGroup group : groupList){
							list.add(group.getId());
						}
						int deleteByGroupIdList = groupPriceMapper.deleteByGroupIdList(list);
						if (deleteByGroupIdList != list.size()) {
							status.setRollbackOnly();
							LOGGER.error("groupPriceMapper.deleteByGroupIdList ,params:list={},result:{}",JSON.toJSONString(list),deleteByGroupIdList);
							return 0;
						}
					}
					int deleteByProductSupplierId = groupMapper.deleteByProductSupplierId(productSupplierId);
					if (deleteByProductSupplierId != groupList.size()) {
						status.setRollbackOnly();
						LOGGER.error("groupMapper.deleteByProductSupplierId ,params:productSupplierId={},result:{}",productSupplierId,deleteByProductSupplierId);
						return 0;
					}
					int deleteByPrimaryKey = groupSupplierMapper.deleteByPrimaryKey(productSupplierId);
					if (deleteByPrimaryKey < 1) {
						status.setRollbackOnly();
						LOGGER.error("groupSupplierMapper.deleteByPrimaryKey ,params:productSupplierId={},result:{}",productSupplierId,deleteByPrimaryKey);
						return 0;
					}
					return 1;
				} catch (Exception e) {
					status.setRollbackOnly();
					return 0;
				}
			}
		});
		if (dbResult == null || dbResult <= 0) {
			return 0;
		}
		return dbResult;
	}

	//@Transactional
	//TODO 事务控制？
	@Override
	public void copyProductSuppliersToTarget(Integer toProductId,
			final List<Integer> productSupplierIdList) {
		final Map parameters=new HashMap();
		parameters.put("toProductId", toProductId);
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					for(Integer productSupplierId : productSupplierIdList){
						parameters.put("fromProductSupplierId", productSupplierId);
						Integer copyProductSuppliers = groupSupplierMapper.copyProductSuppliers(parameters);
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
	public List<ProductGroupSupplier> getProductPriceInfoList(Integer productId) {
		return groupSupplierMapper.getProductPriceInfoList(productId);
	}

	/*@Override
	public int save(ProductGroupSupplier groupSupplier) {
		groupSupplier.setCreateTime(System.currentTimeMillis());
		groupSupplierMapper.insertSelective(groupSupplier);
		return groupSupplier.getId();
	}*/

	
	
	

}

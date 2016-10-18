package com.yihg.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.product.api.ProductGroupService;
import com.yihg.product.dao.ProductGroupMapper;
import com.yihg.product.dao.ProductGroupPriceMapper;
import com.yihg.product.dao.ProductGroupSellerMapper;
import com.yihg.product.dao.ProductGroupSupplierMapper;
import com.yihg.product.po.ProductGroup;
import com.yihg.product.po.ProductGroupPrice;
import com.yihg.product.po.ProductGroupSupplier;
import com.yihg.product.vo.ProductGroupVo;

import org.springframework.transaction.annotation.Transactional;

public class ProductGroupServiceImpl implements ProductGroupService {

	@Autowired
	private ProductGroupMapper productGroupMapper;
	@Autowired
	private ProductGroupPriceMapper priceMapper;
	@Autowired
	private ProductGroupSupplierMapper groupSupplierMapper;
	@Autowired
	private ProductGroupSellerMapper sellerMapper;
	
	@Transactional
	@Override
	public int save(ProductGroup productGroup) {
		int i = 0;
		if(productGroup.getId()!=null){			
			i = productGroupMapper.updateByPrimaryKeySelective(productGroup);
			//删除价格组
			if(productGroup.getState()!=null && productGroup.getState()==-1){
				//删除价格组的用户列表和价格表
				afterDelAgencyGroup(productGroup.getId());
			}
		}else{
			productGroup.setState((byte)1);
			productGroup.setCreateTime(System.currentTimeMillis());
			i = productGroupMapper.insertSelective(productGroup);
		}
				
		return i;
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
	@Transactional
	@Override
	public void save2(List<ProductGroup> productGroups,Integer groupSupplierId) {
		//int id=0;
		//if (productGroups!=null && productGroups.size()>0) {
			productGroupMapper.updateStateBySupplierId(groupSupplierId);
		//}
		//List<Integer> idList=new ArrayList<Integer>();
		for (ProductGroup pGroup : productGroups) {
			
			if(pGroup.getId()!=null){
				pGroup.setState((byte)1);
				priceMapper.updateByGroupId(pGroup.getId());
				productGroupMapper.updateByPrimaryKeySelective(pGroup);
				
				for (ProductGroupPrice gPrice : pGroup.getGroupPrices()) {
					gPrice.setGroupId(pGroup.getId());
					gPrice.setState((byte)1);
					gPrice.setCreateTime(System.currentTimeMillis());
					if (gPrice.getId()!=null) {
						priceMapper.updateByPrimaryKeySelective(gPrice);
					}
					else {
						gPrice.setCreateTime(System.currentTimeMillis());
						priceMapper.insertSelective(gPrice);
					}
				}
				
			}else{
				pGroup.setState((byte)1);
				pGroup.setCreateTime(System.currentTimeMillis());
				productGroupMapper.insertSelective(pGroup);
				for (ProductGroupPrice gPrice : pGroup.getGroupPrices()) {
					gPrice.setGroupId(pGroup.getId());
					gPrice.setState((byte)1);
//					if (gPrice.getId()!=null) {
//						gPrice.setState((byte)1);
//						priceMapper.updateByPrimaryKeySelective(gPrice);
//					}
					gPrice.setCreateTime(System.currentTimeMillis());
					priceMapper.insertSelective(gPrice);
				}
			}
		}
		groupSupplierMapper.updateTimeById(groupSupplierId);		
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

	@Transactional
	@Override
	public void copyGroups(List<Integer> groupIdList,
			List<Integer> groupSupplierIdList) {		
		for(Integer groupId : groupIdList){
			ProductGroup groupInfo = productGroupMapper.selectByPrimaryKey(groupId);
			groupInfo.setId(null);
			groupInfo.setState((byte)1);			
			for(Integer groupSupplierId : groupSupplierIdList){		
				groupInfo.setGroupSupplierId(groupSupplierId);
				productGroupMapper.insertSelective(groupInfo);
				priceMapper.copyPrices(groupId, groupInfo.getId());				
			}			
		}
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

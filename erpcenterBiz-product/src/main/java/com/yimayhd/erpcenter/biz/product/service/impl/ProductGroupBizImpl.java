package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;

public class ProductGroupBizImpl implements ProductGroupBiz {

	@Autowired
	private ProductGroupDal productGroupDal;
	
	@Transactional
	@Override
	public int save(ProductGroup productGroup) {
		return productGroupDal.save(productGroup);
	}
	
	@Override
	public List<ProductGroup> selectProductGroups(Integer productId) {
		
		return productGroupDal.selectProductGroups(productId);
	}
	@Override
	public int validateName(String name, Integer productId,Integer id) {
		
		return productGroupDal.validateName(name,productId,id);
	}
	@Override
	public ProductGroup getGroupInfoById(Integer groupId) {		
		return productGroupDal.getGroupInfoById(groupId);
	}
	@Override
	public List<ProductGroup> selectProductGroupList(Integer groupSupplierId) {
		return productGroupDal.selectProductGroupList(groupSupplierId);
	}
	@Transactional
	@Override
	public void save2(List<ProductGroup> productGroups,Integer groupSupplierId) {
		productGroupDal.save2(productGroups, groupSupplierId);
	}

	@Override
	public int save3(ProductGroup productGroup) {
		return productGroupDal.save3(productGroup);
	}

	@Override
	public List<ProductGroupVo> selectGroupsByProdctIdAndSupplierId(
			Integer productId, Integer supplierId, Date groupDate) {
		return  productGroupDal.selectGroupsByProdctIdAndSupplierId(productId, supplierId, groupDate);
	}

	@Transactional
	@Override
	public void copyGroups(List<Integer> groupIdList,
			List<Integer> groupSupplierIdList) {
		productGroupDal.copyGroups(groupIdList, groupSupplierIdList);
	}

	@Override
	public List<ProductGroup> selectProductGroupsBySellerId(Integer productId,
			Integer sellerId) {		
		return productGroupDal.selectProductGroupsBySellerId(productId, sellerId);
	}

	@Override
	public void afterDelAgencyGroup(Integer groupId) {
		productGroupDal.afterDelAgencyGroup(groupId);
	}

	
	
	

}

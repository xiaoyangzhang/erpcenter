package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductGroupSellerBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSeller;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSellerDal;


public class ProductGroupSellerBizImpl implements ProductGroupSellerBiz {
	@Autowired
	private ProductGroupSellerDal productGroupSellerDal;
	
	@Override
	public List<ProductGroupSeller> selectGroupSellerList(Integer bizId,
			Integer groupId, Integer productId) {
		
		return productGroupSellerDal.selectGroupSellerList(bizId,groupId,productId);
	}

	@Override
	public ProductGroupSeller selectGroupSeller(Integer bizId,
			Integer productId, Integer operateId) {
		return productGroupSellerDal.selectGroupSeller(bizId,productId,operateId);
	}

	@Override
	public int insertSelective(ProductGroupSeller productGroupSeller) {
		return productGroupSellerDal.insertSelective(productGroupSeller);
	}

	@Override
	public int delSeller(Integer id) {
		return productGroupSellerDal.delSeller(id);
	}

	@Override
	public String selectExpSellersByProductId(Integer bizId,
			Integer productId) {
		return productGroupSellerDal.selectExpSellersByProductId(bizId, productId);
	}

	@Override
	public List<ProductGroupSeller> selectGroupSellers(Integer bizId,
			String operateIds) {
		return productGroupSellerDal.selectGroupSellers(bizId, operateIds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yihg.product.api.ProductGroupSellerService#delSellerBatch(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int delSellerBatch(Integer groupId, Integer productId,
			Integer operatorId) {
		return productGroupSellerDal.delSellerBatch(groupId, productId, operatorId);
	}

	@Override
	public int insertByBatch(ProductGroupSeller item) {
		return productGroupSellerDal.insertByBatch(item);
	}

}

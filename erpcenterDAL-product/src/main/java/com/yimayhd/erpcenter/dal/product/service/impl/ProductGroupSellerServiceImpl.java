package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.product.api.ProductGroupSellerService;
import com.yihg.product.dao.ProductGroupSellerMapper;
import com.yihg.product.po.ProductGroupSeller;

public class ProductGroupSellerServiceImpl implements ProductGroupSellerService {
	@Autowired
	private ProductGroupSellerMapper productGroupSellerMapper;
	
	@Override
	public List<ProductGroupSeller> selectGroupSellerList(Integer bizId,
			Integer groupId, Integer productId) {
		
		return productGroupSellerMapper.selectGroupSellerList(bizId,groupId,productId);
	}

	@Override
	public ProductGroupSeller selectGroupSeller(Integer bizId,
			Integer productId, Integer operateId) {
		return productGroupSellerMapper.selectGroupSeller(bizId,productId,operateId);
	}

	@Override
	public int insertSelective(ProductGroupSeller productGroupSeller) {
		return productGroupSellerMapper.insertSelective(productGroupSeller);
	}

	@Override
	public int delSeller(Integer id) {
		return productGroupSellerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String selectExpSellersByProductId(Integer bizId,
			Integer productId) {
		List<ProductGroupSeller> list = productGroupSellerMapper.selectSellerListByProductId(bizId, productId);
		String ids = "";
		if(list!=null && list.size()>0){
			for(ProductGroupSeller seller : list){
				ids+=seller.getOperatorId()+",";
			}			
		}
		if(StringUtils.isNotEmpty(ids)){
			return ids.substring(0, ids.length()-1);
		}
		return ids;
	}

	@Override
	public List<ProductGroupSeller> selectGroupSellers(Integer bizId,
			String operateIds) {
		return productGroupSellerMapper.selectGroupSellers(bizId, operateIds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yihg.product.api.ProductGroupSellerService#delSellerBatch(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int delSellerBatch(Integer groupId, Integer productId,
			Integer operatorId) {
		return productGroupSellerMapper.deleteByBatch(groupId, productId, operatorId);
	}

	@Override
	public int insertByBatch(ProductGroupSeller item) {
		return productGroupSellerMapper.insertByBatch(item);
	}

}

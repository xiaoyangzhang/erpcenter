package com.yimayhd.erpcenter.biz.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSeller;


public interface ProductGroupSellerBiz {

	List<ProductGroupSeller> selectGroupSellerList(Integer bizId, Integer groupId,
			Integer productId);

	ProductGroupSeller selectGroupSeller(Integer bizId, Integer productId, Integer operateId);
	List<ProductGroupSeller> selectGroupSellers(Integer bizId,  String operateIds);

	int insertSelective(ProductGroupSeller productGroupSeller);

	int delSeller(Integer id);
	
	String selectExpSellersByProductId(Integer bizId,Integer productId);
	
	/*
	 * 组团版，批量删除用户的产品访问权限（页面：产品价格〉编辑价格〉批量设置用户） ou 2016-5-27
	 */
	int delSellerBatch(Integer groupId,Integer productId,Integer operatorId);
	int insertByBatch(ProductGroupSeller item);
}

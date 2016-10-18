package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceStockallocateBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPriceStockallocate;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupPriceStockallocateDal;


public class ProductGroupPriceStockallocateBizImpl implements ProductGroupPriceStockallocateBiz {

	@Autowired
	private ProductGroupPriceStockallocateDal productGroupPriceStockallocateDal;
	@Override
	public List<ProductGroupPriceStockallocate> selectByPrimaryKey(
			Integer priceId) {
		
		return productGroupPriceStockallocateDal.selectByPrimaryKey(priceId);
	}

	
}

package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.mapper.ProductGroupPriceStockallocateMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPriceStockallocate;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupPriceStockallocateDal;

public class ProductGroupPriceStockallocateDalImpl implements ProductGroupPriceStockallocateDal {

	@Autowired
	private ProductGroupPriceStockallocateMapper stockallocateMapper;
	@Override
	public List<ProductGroupPriceStockallocate> selectByPrimaryKey(
			Integer priceId) {
		
		return stockallocateMapper.selectByPrimaryKey(priceId);
	}

	
}

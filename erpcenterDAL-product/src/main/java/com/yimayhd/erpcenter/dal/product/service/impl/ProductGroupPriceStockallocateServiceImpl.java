package com.yihg.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.product.api.ProductGroupPriceService;
import com.yihg.product.api.ProductGroupPriceStockallocateService;
import com.yihg.product.dao.ProductGroupPriceStockallocateMapper;
import com.yihg.product.po.ProductGroupPriceStockallocate;

public class ProductGroupPriceStockallocateServiceImpl implements ProductGroupPriceStockallocateService {

	@Autowired
	private ProductGroupPriceStockallocateMapper stockallocateMapper;
	@Override
	public List<ProductGroupPriceStockallocate> selectByPrimaryKey(
			Integer priceId) {
		
		return stockallocateMapper.selectByPrimaryKey(priceId);
	}

	
}

package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierService;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

public class ProductGroupSupplierServiceImpl implements ProductGroupSupplierService {

	@Autowired
//	private ProductGroupSupplierMapper groupSupplierMapper;

	@Override
	public List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition condition) {		
		//return groupSupplierMapper.selectSupplierList(condition);
		return null;
	}
}

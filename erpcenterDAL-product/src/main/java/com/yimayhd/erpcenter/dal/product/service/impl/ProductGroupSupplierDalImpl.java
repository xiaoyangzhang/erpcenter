package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.service.ProductGroupSupplierDal;



/**
 * 
* @ClassName: ProductGroupSupplierDalImpl 
* @Description: 
* @author guohongfei
* @date 2016年10月18日 下午10:32:01 
*
 */
public class ProductGroupSupplierDalImpl implements ProductGroupSupplierDal {
	
	@Autowired
//	private ProductGroupSupplierMapper groupSupplierMapper;

	@Override
	public List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition condition) {		
		//return groupSupplierMapper.selectSupplierList(condition);
		return null;
	}
	
}

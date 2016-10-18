package com.yimayhd.erpcenter.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;



/**
 * 
* @ClassName: ProductGroupSupplierService 
* @Description: 
* @author guohongfei
* @date 2016年10月18日 下午10:32:01 
*
 */
public interface ProductGroupSupplierDal {
	
	List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition supplier);
	
}

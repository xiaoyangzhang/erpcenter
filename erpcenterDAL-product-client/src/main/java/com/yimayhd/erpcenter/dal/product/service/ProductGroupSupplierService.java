package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

/**
 * @author : xuzejun
 * @date : 2015年7月2日 下午3:58:56
 * @Description: 价格组接口
 */
public interface ProductGroupSupplierService {
	
	List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition supplier);
	
}

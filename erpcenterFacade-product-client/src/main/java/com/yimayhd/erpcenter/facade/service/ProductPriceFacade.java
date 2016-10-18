package com.yimayhd.erpcenter.facade.service;

import com.yimayhd.erpcenter.facade.query.ProductGroupSupplierDTO;
import com.yimayhd.erpcenter.facade.result.ResultSupport;



/**
 * 
* @ClassName: ProductPriceFacade 
* @Description: 
* @author hongfei.guo
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface ProductPriceFacade {
	
	
	/**
	 * 保存组团社
	 * @param data
	 * @return
	 */
	ResultSupport supplierSave(String data);
	
	/**
	 * 删除组团社
	 * @param groupSupplier
	 * @return
	 */
	ResultSupport delSupplier(ProductGroupSupplierDTO productGroupSupplierDTO);
	
}

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
public interface ProductPricePlusFacade {
	
	
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
	
	/**
	 * 复制到其他产品
	 * @param data
	 * @param productId
	 * @return
	 */
	ResultSupport copyProductSuppliers(String data, Integer productId);
	
	/**
	 * 库存设置
	 * @param productId
	 * @param stockStr
	 * @param year
	 * @param month
	 * @return
	 */
	ResultSupport saveStock(Integer productId,String stockStr,Integer year,Integer month);
	
	/**
	 * 添加价格组
	 * @param productGroups
	 * @param groupSupplierId
	 * @return
	 */
	public ResultSupport savePriceGroup(String productGroups,Integer groupSupplierId);
	
	/**
	 * 将价格组复制到其他组
	 * @param model
	 * @param groupIds
	 * @param destGroupSupplierIds
	 * @return
	 */
	public ResultSupport copyGroup(String groupIds,String destGroupSupplierIds);
}

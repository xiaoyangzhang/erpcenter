package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
import com.yimayhd.erpcenter.facade.service.ProductPriceFacade;



/**
 * 
* @ClassName: ProductPriceFacade 
* @Description: 
* @author hongfei.guo
* @date 2016年10月17日 下午4:26:09 
*
 */
public class ProductPriceFacadeImpl implements ProductPriceFacade{
	
	@Autowired
	private ProductGroupSupplierDal groupSupplierDal;
	
	/**
	 * 保存组团社
	 * @param data
	 * @return
	 */
	public ResultSupport supplierSave(String data){
		
		ResultSupport result = new ResultSupport();
		
		List<ProductGroupSupplier> saveP = JSONArray.parseArray(data, ProductGroupSupplier.class);
		if(saveP.size()==0){
			return result;
		}
		int ret = groupSupplierDal.save(saveP);
		if(ret != 1){
			result.setSuccess(false);
			result.setResultMsg("操作失败！");
		}
		
		return result;
	}
	
}

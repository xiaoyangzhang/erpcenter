package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ProductGroupSupplierDTO;
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
	private ProductGroupSupplierDal productGroupSupplierDal;
	
	/**
	 * 保存组团社
	 * @param data
	 * @return
	 */
	public ResultSupport supplierSave(String data){
		
		ResultSupport result = new ResultSupport();
		
		if(StringUtils.isEmpty(data)){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.PARAM_ERROR.getErrorMsg());
			return result;
		}
		
		List<ProductGroupSupplier> saveP = null;
		try{
			saveP = JSONArray.parseArray(data, ProductGroupSupplier.class);
			if(saveP.size()==0){
				return result;
			}
		}catch(Exception ex){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.PARAM_ERROR.getErrorMsg());
			return result;
		};
		
		int ret = productGroupSupplierDal.save(saveP);
		if(ret != 1){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.SYSTEM_ERROR.getErrorMsg());
			return result;
		}
		
		return result;
	}
	
	/**
	 * 删除组团社
	 * @param groupSupplier
	 * @return
	 */
	public ResultSupport delSupplier(ProductGroupSupplierDTO productGroupSupplierDTO) {
		
		ResultSupport result = new ResultSupport();
		
		if(productGroupSupplierDTO == null || productGroupSupplierDTO.getId() == null){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.PARAM_ERROR.getErrorMsg());
			return result;
		}
		
		int ret = productGroupSupplierDal.deleteByProductSupplierId(productGroupSupplierDTO.getId());
		if(ret != 1){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.SYSTEM_ERROR.getErrorMsg());
			return result;
		}
		
		return result;
	}
	
}

package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupDal;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ProductGroupSupplierDTO;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
import com.yimayhd.erpcenter.facade.service.ProductPricePlusFacade;



/**
 * 
* @ClassName: ProductPriceFacade 
* @Description: 
* @author hongfei.guo
* @date 2016年10月17日 下午4:26:09 
*
 */
public class ProductPricePlusFacadeImpl implements ProductPricePlusFacade{
	
	@Autowired
	private ProductGroupSupplierDal productGroupSupplierDal;
	
	@Autowired
	private ProductStockDal productStockDal;
	
	@Autowired
	private ProductGroupDal productGroupDal;
	
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
	
	/**
	 * 复制到其他产品
	 * @param data
	 * @param productId
	 * @return
	 */
	public ResultSupport copyProductSuppliers(String data, Integer productId){
		
		ResultSupport result = new ResultSupport();
		
		//获取要复制的组团社
		List<Integer> productSupplierIdList = JSON.parseArray(data, Integer.class);		
		//获取要复制的产品下的组团社
		try {
			productGroupSupplierDal.copyProductSuppliersToTarget(productId, productSupplierIdList);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.SYSTEM_ERROR.getErrorMsg());
			return result;
		}
		return result;
	}
	
	/**
	 * 库存设置
	 * @param productId
	 * @param stockStr
	 * @param year
	 * @param month
	 * @return
	 */
	public ResultSupport saveStock(Integer productId,String stockStr,Integer year,Integer month){
		
		ResultSupport result = new ResultSupport();
		if(productId==null || StringUtils.isBlank(stockStr)){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.SYSTEM_ERROR.getErrorMsg());
			return result;
		}
		
		String beginDateStr = year+"-"+(month<10 ? ("0"+month):(""+month))+"-01";
    	String endDateStr = month==12 ? ((year+1)+"-01-01"):(year+"-"+(month<9 ? ("0"+(month+1)):(""+(month+1)))+"-01");    	
    	Date startDate = DateUtils.parse(beginDateStr, "yyyy-MM-dd");
    	Date endDate = DateUtils.parse(endDateStr,"yyyy-MM-dd");
		List<ProductStock> stockList = JSON.parseArray(stockStr, ProductStock.class);
		productStockDal.saveStock(productId,stockList,startDate,endDate);
		return result;
	}
	
	/**
	 * 添加价格组
	 * @param productGroups
	 * @param groupSupplierId
	 * @return
	 */
	public ResultSupport savePriceGroup(String productGroups,Integer groupSupplierId){
		
		ResultSupport result = new ResultSupport();
		
		List<ProductGroup> GroupPrices = JSON.parseArray(productGroups, ProductGroup.class);
		try {
			productGroupDal.save2(GroupPrices,groupSupplierId);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR.getErrorCode());
			result.setResultMsg(ProductErrorCode.SYSTEM_ERROR.getErrorMsg());
			return result;
		}
		return result;
	}
	
	/**
	 * 将价格组复制到其他组
	 * @param model
	 * @param groupIds
	 * @param destGroupSupplierIds
	 * @return
	 */
	public ResultSupport copyGroup(String groupIds,String destGroupSupplierIds){

		ResultSupport result = new ResultSupport();

		List<Integer> groupIdList = JSON.parseArray(groupIds, Integer.class);
		List<Integer> groupSupplierIdList = JSON.parseArray(destGroupSupplierIds, Integer.class);
		productGroupDal.copyGroups(groupIdList,groupSupplierIdList);
		return result;
	}
}

package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupDal;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupPriceDal;
import com.yimayhd.erpcenter.dal.product.service.ProductGroupSupplierDal;
import com.yimayhd.erpcenter.dal.product.service.ProductInfoDal;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ProductGroupSupplierDTO;
import com.yimayhd.erpcenter.facade.query.ProductSupplierConditionDTO;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
import com.yimayhd.erpcenter.facade.result.ToAddPriceGroupResult;
import com.yimayhd.erpcenter.facade.result.ToSupplierListResult;
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
	
	@Autowired
	private ProductInfoDal productInfoDal;
	
	@Autowired
    private ProductGroupPriceDal productGroupPriceDal;
	
	/**
	 * 保存组团社
	 * @param data
	 * @return
	 */
	public ResultSupport supplierSave(String data){
		
		ResultSupport result = new ResultSupport();
		
		if(StringUtils.isEmpty(data)){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
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
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
			return result;
		};
		
		int ret = productGroupSupplierDal.save(saveP);
		if(ret != 1){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
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
		
		if(productGroupSupplierDTO == null || productGroupSupplierDTO.getGroupSupplier() == null){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
			return result;
		}
		
		int ret = productGroupSupplierDal.deleteByProductSupplierId(productGroupSupplierDTO.getGroupSupplier().getId());
		if(ret != 1){
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
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
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
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
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
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
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
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
	
	/**
	 * 跳转到价格设置页面
	 * @param model
	 * @param condition
	 * @return
	 */
	public ToSupplierListResult toSupplierList(ProductSupplierConditionDTO conditionDTO) {
		
		ProductSupplierCondition condition = conditionDTO.getCondition();
		
		ToSupplierListResult result = new ToSupplierListResult();
		result.setProductId(condition.getProductId());
		ProductInfo productInfo = productInfoDal.findProductInfoById(condition.getProductId());
		result.setProductName("【" +productInfo.getBrandName()+"】"+productInfo.getNameCity());
		result.setGroupSuppliers(productGroupSupplierDal.selectSupplierList(conditionDTO.getCondition()));
		result.setSupplierName(condition.getSupplierName());
		result.setCity(condition.getCity());
		
		return result;
	}
	
	/**
	 * 跳转到添加价格组页面
	 * @param id 产品组团社表id
	 * @return
	 */
	public ToAddPriceGroupResult addPriceGroup(Integer id){
		
		ToAddPriceGroupResult result = new ToAddPriceGroupResult();
		
		List<ProductGroup> productGroups = productGroupDal.selectProductGroupList(id);
		ProductInfo productInfo = productInfoDal.findProductInfoBySupplierId(id);
		for (ProductGroup pGroup : productGroups) {
			List<ProductGroupPrice> groupPrices = productGroupPriceDal.selectProductGroupPrices(pGroup.getId(), null, null);
			pGroup.setGroupPrices(groupPrices);
		}
		result.setProductGroups(productGroups);
		
		ProductGroupSupplier supplierInfo = productGroupSupplierDal.selectgGroupSupplierById(id);
		result.setSupplierName(supplierInfo.getSupplierName());
		result.setSupplierId(supplierInfo.getSupplierId());
		result.setGroupSupplierId(id);
		result.setProductId(supplierInfo.getProductId());
		result.setBrandName(productInfo.getBrandName());
		result.setProductName(productInfo.getNameCity());
		
		return result;
	}
	
	/**
	 * 根据年、月得到产品的库存
	 * @param productId
	 * @param year
	 * @param month
	 * @return
	 */
	public String stockMonth(Integer productId,Integer year,Integer month){
		String beginDateStr = year+"-"+(month<10 ? ("0"+month):(""+month))+"-01";
    	String endDateStr = month==12 ? ((year+1)+"-01-01"):(year+"-"+(month<9 ? ("0"+(month+1)):(""+(month+1)))+"-01");    	
    	Date startDate = DateUtils.parse(beginDateStr, "yyyy-MM-dd");
    	Date endDate = DateUtils.parse(endDateStr,"yyyy-MM-dd");
		List<ProductStock> list = productStockDal.getStocksByProductIdAndDateSpan(productId, startDate, endDate);
		
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("data", JSON.toJSONString(list));
		return json.toString();
	}
}

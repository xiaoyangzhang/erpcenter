
package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupExtraItemBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.vo.PriceCopyVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductPriceVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ProductGroupSupplierDTO;
import com.yimayhd.erpcenter.facade.query.ProductSupplierConditionDTO;
import com.yimayhd.erpcenter.facade.result.ProductGroupResult;
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
	private ProductGroupSupplierBiz productGroupSupplierBiz;
	@Autowired
	private ProductStockBiz productStockBiz;
	
	@Autowired
	private ProductGroupBiz productGroupBiz;
	
	@Autowired
	private ProductInfoBiz productInfoBiz;
	
	@Autowired
    private ProductGroupPriceBiz productGroupPriceBiz;
	@Autowired
	private ProductGroupExtraItemBiz productGroupExtraItemBiz;
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
		
		int ret = productGroupSupplierBiz.save(saveP);
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
		
		int ret = productGroupSupplierBiz.deleteByProductSupplierId(productGroupSupplierDTO.getGroupSupplier().getId());
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
			productGroupSupplierBiz.copyProductSuppliersToTarget(productId, productSupplierIdList);
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
		productStockBiz.saveStock(productId,stockList,startDate,endDate);
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
			productGroupBiz.save2(GroupPrices,groupSupplierId);
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
		productGroupBiz.copyGroups(groupIdList,groupSupplierIdList);
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
		ProductInfo productInfo = productInfoBiz.findProductInfoById(condition.getProductId());
		result.setProductName("【" +productInfo.getBrandName()+"】"+productInfo.getNameCity());
		result.setGroupSuppliers(productGroupSupplierBiz.selectSupplierList(conditionDTO.getCondition()));
		return result;
	}
	
	/**
	 * 跳转到添加价格组页面
	 * @param id 产品组团社表id
	 * @return
	 */
	public ToAddPriceGroupResult addPriceGroup(Integer id){
		
		ToAddPriceGroupResult result = new ToAddPriceGroupResult();
		
		List<ProductGroup> productGroups = productGroupBiz.selectProductGroupList(id);
		ProductInfo productInfo = productInfoBiz.findProductInfoBySupplierId(id);
		for (ProductGroup pGroup : productGroups) {
			List<ProductGroupPrice> groupPrices = productGroupPriceBiz.selectProductGroupPrices(pGroup.getId(), null, null);
			pGroup.setGroupPrices(groupPrices);
		}
		result.setProductGroups(productGroups);
		
		ProductGroupSupplier supplierInfo = productGroupSupplierBiz.selectgGroupSupplierById(id);
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
		List<ProductStock> list = productStockBiz.getStocksByProductIdAndDateSpan(productId, startDate, endDate);
		return JSON.toJSONString(list);
	}
	
	/**
	 * 将某个产品下的组团社和价格组复制到其他产品
	 * @param data
	 * @param productId
	 * @return
	 */ 
	public ResultSupport copyProduct(String data,Integer productId){
		
		ResultSupport result = new ResultSupport();
		//获取要复制到的产品的id集合
		List<ProductInfo> productInfos = JSON.parseArray(data, ProductInfo.class);
		//获取要复制的产品下的组团社
		try {
			productGroupSupplierBiz.save(productInfos, productId);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public List<Map> loadMinPrice(List<Integer> productIds, Date date) {
		List<Map> mapList = productGroupPriceBiz.getMinPriceByProductIdSetAndDate(productIds, date);
		return mapList;
	}

	@Override
	public ProductGroupResult toPriceSetting(Integer productId) {
		ProductGroupResult result = new ProductGroupResult();
		List<ProductGroup> selectProductGroups = productGroupBiz.selectProductGroups(productId);
		//查询价格组，计算是否过期
		Date nowdate=new Date(); 
		for (ProductGroup productGroup : selectProductGroups) {
			List<ProductGroupPrice> list = productGroupPriceBiz.selectProductGroupPrices(productGroup.getId(), null, null);
			List<String> yflag=new ArrayList<String>();//过期
			List<String> nflag=new ArrayList<String>();//未过期
			for (ProductGroupPrice pList : list) {
				boolean flag = pList.getGroupDateTo().before(nowdate);
				if(flag){//过期
					yflag.add("1");
				}else{//没有过期
					nflag.add("2");
				}
				
			}
			if(yflag.isEmpty()){
				productGroup.setFlag("未过期");
			}
			if(nflag.isEmpty()){
				productGroup.setFlag("已过期");
			}
			if(!yflag.isEmpty()&&!nflag.isEmpty()){
				productGroup.setFlag("部分过期");
			}
				
		}
		result.setProductGroups(selectProductGroups);
		ProductInfo productInfo = productInfoBiz.findProductInfoById(productId);
		result.setProductInfo(productInfo);
		return result;
	}

	@Override
	public ResultSupport save(ProductGroup productGroup) {
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = productGroupBiz.save(productGroup);
		if (saveResult < 1) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public String validateName(String name, Integer productId, Integer id) {
		int validateResult = productGroupBiz.validateName(name,productId,id);
		if (validateResult > 0) {
			return "false";
		}
		return "true";
	}

	@Override
	public ResultSupport copyGroupPrice(PriceCopyVo copyVo) {
		ResultSupport resultSupport = new ResultSupport();
		int copyResult = productGroupPriceBiz.copyGroupPrice(copyVo);
		if (copyResult == 0 ) {
			resultSupport.setErrorCode(ProductErrorCode.NO_DATA_ARRANGE);
		}
		return resultSupport;
	}

	@Override
	public List<ProductGroupPrice> selectPriceByGroupId(Integer groupId) {
		List<ProductGroupPrice> priceList = productGroupPriceBiz.selectPriceByGroupId(groupId);;
		return priceList;
	}

	@Override
	public ResultSupport batchInsertPriceGroup(Integer bizId, String json) {
		ResultSupport resultSupport = new ResultSupport();
		productGroupPriceBiz.batchInsertPriceGroup(bizId,json);
		return resultSupport;
	}

	@Override
	public ProductGroupExtraItem save(
			ProductGroupExtraItem productGroupExtraItem) {
		ProductGroupExtraItem extraItem = productGroupExtraItemBiz.save(productGroupExtraItem);
		return extraItem;
	}

	@Override
	public ProductGroupExtraItem edit(
			ProductGroupExtraItem productGroupExtraItem) {
		
		ProductGroupExtraItem extraItem = productGroupExtraItemBiz.edit(productGroupExtraItem);
		return extraItem;
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean result = productGroupExtraItemBiz.deleteById(id);
		return result;
	}

	@Override
	public ProductGroupExtraItem findById(Integer id) {
		ProductGroupExtraItem extraItem = productGroupExtraItemBiz.findById(id);
		return extraItem;
	}

	@Override
	public ProductGroupResult queryGroupPriceList(PageBean pageBean,
			Map paramMap) {
		ProductGroupResult result = new ProductGroupResult();
		PageBean page = productInfoBiz.findProductInfos(pageBean, paramMap);
		result.setPageBean(page);
		Map<Integer, String> priceStateMap = new HashMap<Integer, String>();
		for(Object product : pageBean.getResult()){
			ProductInfo info = (ProductInfo) product;
			Integer productId = info.getId();
			String state = productInfoBiz.getProductPriceState(productId);
			priceStateMap.put(info.getId(), state);
		}
		result.setMap(priceStateMap);
		return result;
	}

	@Override
	public ProductGroupResult ToAddPrice(Integer groupId, Integer productId) {
		ProductGroupResult result = new ProductGroupResult();
		ProductInfo info = productInfoBiz.findProductInfoById(productId);
		result.setProductInfo(info);
		//查询客户列表
		List<ProductGroupSupplier> suppliers = productGroupSupplierBiz.selectProductGroupSuppliers(groupId);
		result.setGroupSuppliers(suppliers);
		ProductGroup groupInfo = productGroupBiz.getGroupInfoById(groupId);
		List<ProductGroup> productGroups = new ArrayList<ProductGroup>();
		productGroups.add(groupInfo);
		result.setProductGroups(productGroups);
		return result;
	}

	@Override
	public ProductGroupResult ToEditPrice(Integer groupId, Integer productId,Integer productGroupPriceId) {
		ProductGroupResult result = new ProductGroupResult();
		ProductGroupResult toAddPriceResult = ToAddPrice(groupId, productId);
		result.setGroupSuppliers(toAddPriceResult.getGroupSuppliers());
		result.setProductGroups(toAddPriceResult.getProductGroups());
		result.setProductInfo(toAddPriceResult.getProductInfo());
		ProductPriceVo vo = productGroupPriceBiz.selectByPrimaryKey(productGroupPriceId);
		result.setPriceVo(vo);
		return result;
	}

	@Override
	public ResultSupport save(ProductPriceVo priceVo) {
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = productGroupPriceBiz.save(priceVo);
		if (saveResult < 1) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public List<ProductGroupPrice> selectProductGroupPrices(Integer groupId,
			String year, String month) {
		List<ProductGroupPrice> prices = productGroupPriceBiz.selectProductGroupPrices(groupId, year, month);
		return prices;
	}

	@Override
	public ResultSupport delete(Integer id) {
		ResultSupport resultSupport = new ResultSupport();
		boolean deleteResult = productGroupPriceBiz.delete(id);
		if (!deleteResult) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ResultSupport batchDel(String[] idArr) {
		ResultSupport resultSupport = new ResultSupport();
		boolean success = true;
		 for (String id : idArr) {
	            try {
	            	productGroupPriceBiz.delete(Integer.valueOf(id));
	                success &= true;
	            } catch (Exception e) {
	                success &= false;
	            }
	        }
		 if (!success) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	
	@Override
	public ToSupplierListResult toSupplierList2(ProductSupplierConditionDTO conditionDTO) {
		
		ProductInfo productInfo = productInfoBiz.findProductInfoById(conditionDTO.getProductId());
		
		List<ProductGroupSupplier>  supplierList = productGroupSupplierBiz.selectSupplierList(conditionDTO.getCondition());
		
		ToSupplierListResult result = new ToSupplierListResult();
		result.setGroupSuppliers(supplierList);
		result.setProductInfo(productInfo);
		
		return result;
	}
}

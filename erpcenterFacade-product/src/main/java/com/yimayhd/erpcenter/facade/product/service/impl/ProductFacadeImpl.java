
package com.yimayhd.erpcenter.facade.product.service.impl;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSellerBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductTagBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSeller;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRight;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductTag;
import com.yimayhd.erpcenter.dal.product.vo.DictWithSelectInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ComponentProductListDTO;
import com.yimayhd.erpcenter.facade.query.ProductListParam;
import com.yimayhd.erpcenter.facade.query.ProductPriceListDTO;
import com.yimayhd.erpcenter.facade.query.ProductRemarkDTO;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
import com.yimayhd.erpcenter.facade.query.ProductTagDTO;
import com.yimayhd.erpcenter.facade.result.ComponentProductListResult;
import com.yimayhd.erpcenter.facade.result.GetProductRouteResult;
import com.yimayhd.erpcenter.facade.result.ProductDataRightResult;
import com.yimayhd.erpcenter.facade.result.ProductInfoResult;
import com.yimayhd.erpcenter.facade.result.ProductPriceListResult;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
import com.yimayhd.erpcenter.facade.result.ToProductAddResult;
import com.yimayhd.erpcenter.facade.result.ToProductRemarkResult;
import com.yimayhd.erpcenter.facade.result.ToProductTagResult;
import com.yimayhd.erpcenter.facade.result.WebResult;
import com.yimayhd.erpcenter.facade.service.ProductFacade;

/**
 * 
* @ClassName: ProductFacadeImpl 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:51:55 
*
 */
public class ProductFacadeImpl implements ProductFacade{
	private static final Logger LOGGER = LoggerFactory.getLogger("ProductFacadeImpl");
	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private ProductRouteBiz productRouteBiz;
	@Autowired
	private ProductTagBiz productTagBiz;
	@Autowired
	private ProductRemarkBiz productRemarkBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private ProductGroupSupplierBiz productGroupSupplierBiz;
	
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private ProductGroupSellerBiz productGroupSellerBiz;
	@Override
	public int saveBasicInfo(ProductSaveDTO productSaveDTO) {
		if(null == productSaveDTO || null == productSaveDTO.getProductInfoVo()){
			return -1;
		}
		ProductInfoVo productInfoVo = productSaveDTO.getProductInfoVo();
		ProductInfo productInfo = productInfoVo.getProductInfo();
		Integer pid = productInfo.getId();
		if (pid == null) {
			productInfo.setCreatorId(productSaveDTO.getCreateId());
			productInfo.setCreatorName(productSaveDTO.getCreateName());
			productInfo.setBizId(productSaveDTO.getBizId());
			productInfoVo.setOrgIdSet(productInfoVo.getOrgIdSet());
		}
		String brandCode = dicBiz.getById(productInfo.getBrandId()+"").getCode();
		int id = productInfoBiz.saveProductInfo(productInfoVo, productSaveDTO.getBizCode(), brandCode);
		boolean result = false;
		if (pid == null) {
			productSaveDTO.getProductRouteVo().setProductId(id);
			result = productRouteBiz.saveProductRoute(productSaveDTO.getProductRouteVo());
		}else{
			result = productRouteBiz.editProductRoute(productSaveDTO.getProductRouteVo());
		}
		if(result){
			return id;
		}
		return -1;
	}
	
	@Override
	public WebResult<PageBean<ProductInfo>> selectProductList(PageBean pageBean,
			Map<String, Object> parameters) {
		WebResult<PageBean<ProductInfo>> result = new WebResult<PageBean<ProductInfo>>(); 
		try {
			PageBean<ProductInfo> pageBeanResult = productInfoBiz.selectProductListPage(pageBean, parameters);
			if (pageBeanResult == null  ) {
				result.setErrorCode(ProductErrorCode.QUERY_ERROR);
				return result;
			}
			result.setValue(pageBeanResult);
			return result;
		} catch (Exception e) {
			LOGGER.error("productInfoBiz.selectProductListPage param:PageBean={},parameters={}, error:{}",JSON.toJSONString(pageBean),JSON.toJSONString(parameters),e);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public boolean codeValidate(int bizId, int productId, String code) {
		return productInfoBiz.checkProductCodeExist(productId, bizId, code);
	}

	@Override
	public boolean saveProductTags(ProductTagDTO productTagDTO) {
		return productTagBiz.saveProductTags(productTagDTO.getProductTagVo());
	}

	@Override
	public boolean saveProductRemark(ProductRemarkDTO productRemarkDTO) {
		return productRemarkBiz.saveProductRemark(productRemarkDTO.getProductRemark());
	}

	@Override
	public ToProductAddResult toProductAdd(String typeCode, int bizId) {
		ToProductAddResult toProductAddResult = new ToProductAddResult();
		toProductAddResult.setAllProvince(regionBiz.getAllProvince());
		toProductAddResult.setBrandList(dicBiz.getListByTypeCode(typeCode, bizId));
		return toProductAddResult;
	}

	@Override
	public ToProductAddResult toProductEdit(int productId, String typeCode,
			int bizId) {
		ToProductAddResult toProductAddResult = new ToProductAddResult();
		toProductAddResult.setAllProvince(regionBiz.getAllProvince());
		toProductAddResult.setBrandList(dicBiz.getListByTypeCode(typeCode, bizId));
		toProductAddResult.setProductInfoVo(productInfoBiz.findProductInfoVoById(productId));
		toProductAddResult.setProductRemark(productRemarkBiz.findProductRemarkByProductId(productId));
		return toProductAddResult;
	}

	@Override
	public GetProductRouteResult findProductRouteById(int productId) {
		GetProductRouteResult getProductRouteResult = new GetProductRouteResult();
		getProductRouteResult.setProductRouteVo(productRouteBiz.findByProductId(productId));
		return getProductRouteResult;
	}

	@Override
	public ToProductTagResult toProductTags(int productId, int bizId) {
		ToProductTagResult toProductTagResult = new ToProductTagResult();
		
		List<DicInfo> lineThemeList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_LINE_THEME,bizId);
        List<DicInfo> lineLevelList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_LINE_LEVEL,bizId);
        List<DicInfo> attendMethodList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_ATTEND_METHOD,bizId);
        List<DicInfo> hotelLevelList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_HOTEL_LEVEL,bizId);
        List<DicInfo> daysPeriodList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_DAYS_PERIOD,bizId);
        List<DicInfo> priceRangeList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_PRICE_RANGE,bizId);
        List<DicInfo> exitDestinationList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_EXIT_DESTINATION,bizId);
        List<DicInfo> domesticDestinationList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_DOMESTIC_DESTINATION,bizId);
        List<DicInfo> typeList = dicBiz.getListByTypeCode(Constants.PRODUCT_TAG_TYPE,bizId);
        
        ProductTagVo productTagVo = productTagBiz.findProductTagsByProductId(Integer.valueOf(productId));
		
        Map<String, List<Integer>> selectedMap = new HashMap<String, List<Integer>>();
        for(ProductTag productTag : productTagVo.getProductTags()){
            if(selectedMap.get(productTag.getTagType()) == null){
                List<Integer> tagList = new ArrayList<Integer>();
                tagList.add(productTag.getTagId());
                selectedMap.put(productTag.getTagType(), tagList);
            }else{
                selectedMap.get(productTag.getTagType()).add(productTag.getTagId());
            }
        }

        List<DictWithSelectInfoVo> lineThemeListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_LINE_THEME) != null){
            for(DicInfo dicInfo : lineThemeList){
                lineThemeListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_LINE_THEME).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : lineThemeList){
                lineThemeListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setLineThemeListPlus(lineThemeListPlus);

        List<DictWithSelectInfoVo> lineLevelListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_LINE_LEVEL) != null){
            for(DicInfo dicInfo : lineLevelList){
                lineLevelListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_LINE_LEVEL).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : lineLevelList){
                lineLevelListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setLineLevelListPlus(lineLevelListPlus);

        List<DictWithSelectInfoVo> attendMethodListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_ATTEND_METHOD) != null){
            for(DicInfo dicInfo : attendMethodList){
                attendMethodListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_ATTEND_METHOD).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : attendMethodList){
                attendMethodListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setAttendMethodListPlus(attendMethodListPlus);

        List<DictWithSelectInfoVo> hotelLevelListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_HOTEL_LEVEL) != null){
            for(DicInfo dicInfo : hotelLevelList){
                hotelLevelListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_HOTEL_LEVEL).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : hotelLevelList){
                hotelLevelListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setHotelLevelListPlus(hotelLevelListPlus);

        List<DictWithSelectInfoVo> daysPeriodListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_DAYS_PERIOD) != null){
            for(DicInfo dicInfo : daysPeriodList){
                daysPeriodListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_DAYS_PERIOD).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : daysPeriodList){
                daysPeriodListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setDaysPeriodListPlus(daysPeriodListPlus);
        
        List<DictWithSelectInfoVo> priceRangeListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_PRICE_RANGE) != null){
            for(DicInfo dicInfo : priceRangeList){
            	priceRangeListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_PRICE_RANGE).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : priceRangeList){
            	priceRangeListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setPriceRangeListPlus(priceRangeListPlus);
       
        List<DictWithSelectInfoVo> exitDestinationListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_EXIT_DESTINATION) != null){
            for(DicInfo dicInfo : exitDestinationList){
            	exitDestinationListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_EXIT_DESTINATION).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : exitDestinationList){
            	exitDestinationListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setExitDestinationListPlus(exitDestinationListPlus);
       
        List<DictWithSelectInfoVo> domesticDestinationListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_DOMESTIC_DESTINATION) != null){
            for(DicInfo dicInfo : domesticDestinationList){
            	domesticDestinationListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_DOMESTIC_DESTINATION).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : domesticDestinationList){
            	domesticDestinationListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setDomesticDestinationListPlus(domesticDestinationListPlus);
        
        List<DictWithSelectInfoVo> typeListPlus = new ArrayList<DictWithSelectInfoVo>();
        if(selectedMap.get(Constants.PRODUCT_TAG_TYPE) != null){
            for(DicInfo dicInfo : typeList){
            	typeListPlus.add(new DictWithSelectInfoVo(dicInfo, selectedMap.get(Constants.PRODUCT_TAG_TYPE).contains(dicInfo.getId())));
            }
        }else{
            for(DicInfo dicInfo : typeList){
            	typeListPlus.add(new DictWithSelectInfoVo(dicInfo, false));
            }
        }
        toProductTagResult.setTypeListPlus(typeListPlus);
        
		return toProductTagResult;
	}

	@Override
	public ToProductRemarkResult toProductRemark(int productId) {
		ToProductRemarkResult productRemarkResult = new ToProductRemarkResult();
		productRemarkResult.setProductRemark(productRemarkBiz.findProductRemarkByProductId(productId));
		return productRemarkResult;
	}

	/* (non-Javadoc)
	 * <p>Title: getProductInfoVOById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.yimayhd.erpcenter.facade.service.ProductFacade#getProductInfoVOById(int)
	 */
	@Override
	public ProductInfoVo toEditProductInfoVOById(ProductListParam param) {
		if (param == null || param.getProductId() <= 0 || param.getBizId() <= 0 ||StringUtils.isBlank(param.getTypeCode())) {
			LOGGER.error("param error:ProductListParam={}",JSON.toJSONString(param));
		}
		ProductInfoVo productInfoVo = productInfoBiz.findProductInfoVoById(param.getProductId());
		if (productInfoVo == null) {
			return new ProductInfoVo();
		}
		List<DicInfo> brandList = dicBiz.getListByTypeCode(param.getTypeCode(), param.getBizId());
		productInfoVo.setBrandList(brandList);
		ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(param.getProductId());
		if (productRemark == null) {
			productInfoVo.setProductRemark(new ProductRemark());
		}else {
			
			productInfoVo.setProductRemark(productRemark);
		}
		return productInfoVo;
	}
	
	/**
	 * 产品价格列表
	 * @param productInfo
	 * @param productName
	 * @param name
	 * @return
	 */
	public ProductPriceListResult productPriceList(ProductPriceListDTO productPriceListDTO){
		
		ProductInfo productInfo = productPriceListDTO.getProductInfo();
		int bizId = productInfo.getBizId();
		int page = productPriceListDTO.getPage();
		int pageSize = productPriceListDTO.getPageSize();
		
		// 省市
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		// 产品名称
		List<DicInfo> brandList = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, productInfo.getBizId());
		PageBean pageBean = new PageBean();
		if (page == 0) {
			pageBean.setPage(1);
		}
		if (pageSize == 0) {
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
			
		if (StringUtils.isBlank(productInfo.getOperatorIds())
				&& StringUtils.isNotBlank(productInfo.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = productInfo.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				productInfo.setOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		// productInfo.set
		pageBean.setParameter(productInfo);
		pageBean.setPage(page);
		Map parameters = new HashMap();
		parameters.put("bizId", bizId);
		parameters.put("name", productPriceListDTO.getName());
		parameters.put("productName", productPriceListDTO.getProductName());
		parameters.put("orgId", productPriceListDTO.getOrgId());
		// parameters.put("set", WebUtils.getDataUserIdSet(request));
		pageBean = productInfoBiz.findProductInfos(pageBean, parameters);

		Map<Integer, String> priceStateMap = new HashMap<Integer, String>();
		/*
		 * for (Object product : pageBean.getResult()) { ProductInfo info =
		 * (ProductInfo) product; Integer productId = info.getId(); String state
		 * = productInfoService.getProductPriceState(productId);
		 * priceStateMap.put(info.getId(), state); }
		 */
		
		ProductPriceListResult result = new ProductPriceListResult();
		
		result.setAllProvince(allProvince);;
		result.setBrandList(brandList);
		result.setPage(pageBean);
		result.setPageNum(page);
		result.setPriceStateMap(priceStateMap);
		return result;
	}
	
	@Override
	public ProductInfoResult toProductPreview(int productId) {
		ProductInfoResult result = new ProductInfoResult();
		if (productId <= 0) {
			LOGGER.error("params :productId={}",productId);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			ProductInfoVo productInfoVo = productInfoBiz.findProductInfoVoById(productId);
			result.setProductInfoVo(productInfoVo);
			ProductRouteVo productRouteVo = productRouteBiz.findByProductId(productId);
			result.setProductRouteVo(productRouteVo);
			ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(productId);
			result.setProductRemark(productRemark);
		} catch (Exception e) {
			LOGGER.error("productInfoBiz.findProductInfoVoById ,productRouteBiz.findByProductId,productRemarkBiz.findProductRemarkByProductId,error:{}",e);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
        return result;
	}

	/* (non-Javadoc)
	 * <p>Title: deleteProduct</p> 
	 * <p>Description: </p> 
	 * @param productId
	 * @param state
	 * @return 
	 * @see com.yimayhd.erpcenter.facade.service.ProductFacade#deleteProduct(int, byte)
	 */
	@Override
	public ResultSupport updateProductState(int productId, byte state) {
		ResultSupport result = new ResultSupport();
		List<ProductRoute> productRoutes = productRouteBiz.findProductRouteByProductId(productId);
		if (state != (byte) -1 && productRoutes.size() == 0) {
			result.setErrorCode(ProductErrorCode.PRODUCT_NO_ROUTE_ERROR);
			return result;
		} else {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setState(state);
			productInfo.setId(productId);
			int updateResult = productInfoBiz.updateProductInfo(productInfo);
			if (updateResult != 1) {
				result.setErrorCode(ProductErrorCode.MODIFY_ERROR);
				return result;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * <p>Title: toExportProduct</p> 
	 * <p>Description: </p> 
	 * @param productId
	 * @return 
	 * @see com.yimayhd.erpcenter.facade.service.ProductFacade#toExportProduct(int)
	 */
	@Override
	public WebResult<Map<String, Object>> toExportProduct(int productId) {
		WebResult<Map<String, Object>> result = new WebResult<Map<String,Object>>();
		if (productId <= 0) {
			LOGGER.error("params :productId={}",productId);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			Map<String, Object> map = productInfoBiz.findProductInfos(productId);
			if (map != null) {
				result.setValue(map);
				return result;
			}
			result.setValue(new HashMap<String, Object>());
		} catch (Exception e) {
			LOGGER.error("productInfoBiz.findProductInfos error:{}",e);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	/* (non-Javadoc)
	 * <p>Title: setProductRight</p> 
	 * <p>Description: </p> 
	 * @param productId
	 * @param bizId
	 * @return 
	 * @see com.yimayhd.erpcenter.facade.service.ProductFacade#setProductRight(int, int)
	 */
	@Override
	public ProductDataRightResult getOrgListAndProductRights(int productId, int bizId) {
		ProductDataRightResult result = new ProductDataRightResult();
		if (productId <=0 || bizId <= 0) {
			LOGGER.error("params error:productId={},bizId={}",productId,bizId);
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			List<PlatformOrgPo> orgList = platformOrgBiz.getOrgTree(bizId, null);
			result.setOrgList(orgList);
			List<ProductRight> rightList = productInfoBiz.getRightListByProductId(productId);
			result.setProductRights(rightList);
		} catch (Exception e) {
			LOGGER.error("platformOrgBiz.getOrgTree,productInfoBiz.getRightListByProductId,error:{}",e);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * <p>Title: setProductRight</p> 
	 * <p>Description: </p> 
	 * @param productId
	 * @param bizId
	 * @return 
	 * @see com.yimayhd.erpcenter.facade.service.ProductFacade#setProductRight(int, int)
	 */
	@Override
	public List<Map<String, String>> getProductRight(int productId, int bizId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ProductDataRightResult orgListAndProductRights = getOrgListAndProductRights(productId, bizId);
		List<PlatformOrgPo> orgList = orgListAndProductRights.getOrgList();
		List<ProductRight> rightList = orgListAndProductRights.getProductRights();
		Map<Integer, Boolean> rightMap = new HashMap<Integer, Boolean>();
		if (rightList != null && rightList.size() > 0) {
			for (ProductRight right : rightList) {
				rightMap.put(right.getOrgId(), true);
			}
		}
		// 父节点集合
		// Map<Integer,Boolean> parentMap = new HashMap<Integer,Boolean>();
		if (orgList != null && orgList.size() > 0) {
			/*
			 * for(PlatformOrgPo org : orgList){
			 * parentMap.put(org.getParentId(), true); }
			 */
			for (PlatformOrgPo org : orgList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", org.getOrgId() + "");
				map.put("pId", org.getParentId() + "");
				map.put("name", org.getName());
				map.put("open", "true");
				// 如果当前节点是父节点，则不允许选择
				/*
				 * if(parentMap.containsKey(org.getOrgId())){ map.put("nocheck",
				 * "true"); }
				 */
				if (rightMap.containsKey(org.getOrgId())) {
					map.put("checked", "true");
				}
				list.add(map);
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * <p>Title: setProductRight</p> 
	 * <p>Description: </p> 
	 * @param productId
	 * @param orgIdSet
	 * @return 
	 * @see com.yimayhd.erpcenter.facade.service.ProductFacade#setProductRight(int, java.util.Set)
	 */
	@Override
	public ResultSupport setProductRight(int productId, Set<Integer> orgIdSet) {
		ResultSupport result = new ResultSupport();
		if (productId <= 0 || CollectionUtils.isEmpty(orgIdSet)) {
			LOGGER.error("params error:productId={},orgIdSet={}",productId,JSON.toJSONString(orgIdSet));
			result.setErrorCode(ProductErrorCode.PARAM_ERROR);
			return result;
		}
		try {
			//TODO 需要加返回值判断结果
			productInfoBiz.saveProductRight(productId, orgIdSet);
		} catch (Exception e) {
			LOGGER.error("productInfoBiz.saveProductRight error:{}",e);
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 查询产品列表
	 * @return
	 */
	public ComponentProductListResult componentProductQueryList(ComponentProductListDTO componentProductListDTO){
		
		Integer page = componentProductListDTO.getPage();
		Integer pageSize = componentProductListDTO.getPageSize();
		ProductInfo productInfo = componentProductListDTO.getProductInfo();
		
		PageBean pageBean = new PageBean();
		if (page==null) {
			page=1;
		}
		if(pageSize==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}

		pageBean.setParameter(productInfo);
		pageBean.setPage(page);
		Map parameters=new HashMap();
		parameters.put("bizId", productInfo.getBizId());
		parameters.put("name", null);
		parameters.put("productName", componentProductListDTO.getProductName());
		parameters.put("orgId", componentProductListDTO.getOrgId());
		parameters.put("set", componentProductListDTO.getSet());
	
		pageBean = productInfoBiz.findProductInfos(pageBean, parameters);
		
		ComponentProductListResult result = new ComponentProductListResult();
		result.setPage(pageBean);
		result.setPageNum(page);
		return result;
	}
	
	/**
	 * 产品价格打印预览
	 * @param request
	 * @param response
	 * @param model
	 * @param productIds
	 * @return
	 */
	public List<ProductGroupSupplierVo> productPricePreview(int bizId, String productIds) {
		String[] productIdArr = productIds.split(",");
		List<ProductGroupSupplierVo> productPriceList = new ArrayList<ProductGroupSupplierVo>();
		for (String id : productIdArr) {
			ProductGroupSupplierVo supplierVo = new ProductGroupSupplierVo();
			ProductInfo product = productInfoBiz.findProductByIdAndBizId(Integer.parseInt(id), bizId);
			List<ProductGroupSupplier> productPriceInfoList = productGroupSupplierBiz.getProductPriceInfoList(Integer.parseInt(id));
			if (productPriceInfoList != null && productPriceInfoList.size() > 0) {
				int rowSpan = 0;
				for (ProductGroupSupplier supplier : productPriceInfoList) {
					// rowSpan += (supplier.getRowSpan()==null?0:
					// supplier.getRowSpan());
					List<ProductGroup> productGroupList = supplier
							.getProductGroupList();
					if (productGroupList != null && productGroupList.size() > 0) {
						int rowSpan2 = 0;
						// supplier.setRowSpan(productGroupList.size());
						for (ProductGroup productGroup : productGroupList) {
							productGroup.setRowSpan(productGroup
									.getGroupPrices() == null ? 0
									: productGroup.getGroupPrices().size());
							// productGroup.getGroupPrices();
							rowSpan2 += (productGroup.getGroupPrices() == null ? 0
									: productGroup.getGroupPrices().size());
							rowSpan += (productGroup.getGroupPrices() == null ? 0
									: productGroup.getGroupPrices().size());

						}
						supplier.setRowSpan(rowSpan2);
					}
				}

				product.setRowSpan(rowSpan);
			}
			supplierVo.setProductInfo(product);
			supplierVo.setProductGroupSupplierList(productPriceInfoList);
			productPriceList.add(supplierVo);
		}
		return productPriceList;

	}

	@Override
	public PageBean findProductSales(PageBean pageBean, Integer bizId,
			Integer orgId) {
		PageBean bean=  productInfoBiz.findProductSales(pageBean, bizId,orgId);
		return bean;
	}

	@Override
	public ResultSupport updateUser(ProductInfo addUser, ProductInfo delUser,Integer bizId) {
		ResultSupport resultSupport = new ResultSupport();
		for (ProductGroupSeller pgSeller : addUser.getGroupSellers()) {
			pgSeller.setBizId(bizId);
			pgSeller.setCreateTime(new Date().getTime());
			productGroupSellerBiz.insertByBatch(pgSeller);
		}
		for (ProductGroupSeller pgSeller : delUser.getGroupSellers()) {
			productGroupSellerBiz.delSellerBatch(pgSeller.getGroupId(), pgSeller.getProductId(), pgSeller.getOperatorId());
		}
		return resultSupport;
	}

	@Override
	public List<ProductGroupSupplier> selectProductGroupSuppliers(
			Integer groupId) {
		List<ProductGroupSupplier> groupSuppliers = productGroupSupplierBiz.selectProductGroupSuppliers(groupId);
		return groupSuppliers;
	}

	@Override
	public ResultSupport saveGroupSupplier(
			List<ProductGroupSupplier> groupSuppliers) {
		ResultSupport resultSupport = new ResultSupport();
		List<ProductGroupSupplier> selectP = productGroupSupplierBiz.selectProductGroupSuppliers(groupSuppliers.get(0).getGroupId());
		for (int i = 0; i < selectP.size(); i++) {
			for (int j = 0; j < groupSuppliers.size(); j++) {
				if(groupSuppliers.get(j).getSupplierId().equals(selectP.get(i).getSupplierId())){
					groupSuppliers.remove(j);
				}
			}
		}
		
		int saveResult = productGroupSupplierBiz.save(groupSuppliers) ;
		if (saveResult < 1) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ResultSupport delSupplier(ProductGroupSupplier groupSupplier) {
		ResultSupport resultSupport = new ResultSupport();
		int updateResult = productGroupSupplierBiz.update(groupSupplier) ;
		if (updateResult < 1) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ProductInfoResult productInfoList(PageBean pageBean, Integer bizId,
			String operatorIds) {
		ProductInfoResult result = new ProductInfoResult();
		PageBean page = productInfoBiz.findProductAndPriceGroup(pageBean);
		result.setPageBean(page);
		// 用户
		if (operatorIds != null && operatorIds.length() > 0) {
			List<ProductGroupSeller> groupSellers = productGroupSellerBiz.selectGroupSellers(bizId,
					operatorIds);
			result.setGroupSellers(groupSellers);
		}
		return result;
	}

	@Override
	public ProductRemark findProductRemarkByProductId(Integer productId) {
		ProductRemark productRemark = productRemarkBiz
				.findProductRemarkByProductId(productId);
		return productRemark;
	}

	@Override
	public ProductInfo selectStockCount(Integer productId, String itemDate) {
		ProductInfo info = productInfoBiz.selectStockCount(productId, itemDate);
		return info;
	}

	@Override
	public ProductInfo findProductInfoById(Integer productId) {
		ProductInfo productInfo = productInfoBiz.findProductInfoById(productId);
		return productInfo;
	}

	@Override
	public PageBean getStockStaticsList(StockStaticCondition condition) {
		PageBean bean = productInfoBiz.getStockStaticsList(condition);
		return bean;
	}

	@Override
	public ProductInfoResult productAY_GetProductImport(JSONArray jsonArray,Integer userId,PlatformEmployeePo user) {
		ProductInfoResult result = new ProductInfoResult();
		ProductInfo info =null;
   		ProductRemark remark=null;
   		ProductRoute route=null;
		for(int i = 0;i < jsonArray.size();i++){
       		JSONObject lineObj = jsonArray.getJSONObject(i);
       		ProductInfo product=productInfoBiz.selectProductInfoByPsId(lineObj.getInteger("pid"));
       		if(product!=null&&product.getId()>0){	
       			product.setCode(lineObj.getString("pcode"));
       			product.setNameCity(lineObj.getString("pname"));
       			product.setTravelDays(lineObj.getInteger("pday"));
       			product.setState((byte) 2);
       			product.setProductSysId(lineObj.getInteger("pid"));
       			product.setBizId(user.getBizId());
       			product.setCreatorName(user.getName());
       			product.setCreatorId(userId);
       			product.setCreateTime(new Date().getTime());
           		productInfoBiz.updateProductInfo(product);
           		// product_remark表
           		ProductRemark proRe= productRemarkBiz.findProductRemarkByProductId(product.getId());
           		if(proRe!=null&&proRe.getId()>0){
           			proRe.setGuestNote(lineObj.getString("r_detail"));
           			proRe.setProductFeature(lineObj.getString("pkind"));
           			proRe.setItemInclude(lineObj.getString("r_include"));
           			proRe.setChildPlan(lineObj.getString("r_child"));
           			proRe.setShoppingPlan(lineObj.getString("r_shopping"));
           			proRe.setItemCharge(lineObj.getString("r_selfpay"));
           			proRe.setItemFree(lineObj.getString("r_donate"));
           			proRe.setAttention(lineObj.getString("r_rule"));
               		proRe.setItemOther(lineObj.getString("r_hotel"));
               		proRe.setItemExclude(lineObj.getString("r_include_not"));
               		proRe.setEatNote(lineObj.getString("r_food"));
               		proRe.setCarNote(lineObj.getString("r_car"));
               		proRe.setGuideNote(lineObj.getString("r_guide"));
               		proRe.setInsuranceNote(lineObj.getString("r_insure"));
               		proRe.setAppointRule(lineObj.getString("r_booking_rule"));
               		proRe.setRemarkInfo(lineObj.getString("r_other"));
               		productRemarkBiz.saveProductRemark(proRe);
           		}else{
           			remark=new ProductRemark();
               		remark.setGuestNote(lineObj.getString("r_detail"));
               		remark.setProductFeature(lineObj.getString("pkind"));
               		remark.setItemInclude(lineObj.getString("r_include"));
               		remark.setChildPlan(lineObj.getString("r_child"));
               		remark.setShoppingPlan(lineObj.getString("r_shopping"));
               		remark.setItemCharge(lineObj.getString("r_selfpay"));
               		remark.setItemFree(lineObj.getString("r_donate"));
               		remark.setAttention(lineObj.getString("r_rule"));
               		remark.setItemOther(lineObj.getString("r_hotel"));
               		remark.setItemExclude(lineObj.getString("r_include_not"));
               		remark.setEatNote(lineObj.getString("r_food"));
               		remark.setCarNote(lineObj.getString("r_car"));
               		remark.setGuideNote(lineObj.getString("r_guide"));
               		remark.setInsuranceNote(lineObj.getString("r_insure"));
               		remark.setAppointRule(lineObj.getString("r_booking_rule"));
               		remark.setRemarkInfo(lineObj.getString("r_other"));
             		remark.setProductId(product.getId());
               		productRemarkBiz.saveProductRemark(remark);
           		}
       		}else{
       		info=new ProductInfo();
       		info.setCode(lineObj.getString("pcode"));
       		info.setNameCity(lineObj.getString("pname"));
       		info.setTravelDays(lineObj.getInteger("pday"));
       		info.setState((byte) 2);
       		info.setProductSysId(lineObj.getInteger("pid"));
       		info.setBizId(user.getBizId());
       		info.setCreatorName(user.getName());
       		info.setCreatorId(userId);
       		info.setCreateTime(new Date().getTime());
     		remark=new ProductRemark();
       		remark.setGuestNote(lineObj.getString("r_detail"));
       		remark.setProductFeature(lineObj.getString("pkind"));
       		remark.setItemInclude(lineObj.getString("r_include"));
       		remark.setChildPlan(lineObj.getString("r_child"));
       		remark.setShoppingPlan(lineObj.getString("r_shopping"));
       		remark.setItemCharge(lineObj.getString("r_selfpay"));
       		remark.setItemFree(lineObj.getString("r_donate"));
       		remark.setAttention(lineObj.getString("r_rule"));
       		remark.setItemOther(lineObj.getString("r_hotel"));
       		remark.setItemExclude(lineObj.getString("r_include_not"));
       		remark.setEatNote(lineObj.getString("r_food"));
       		remark.setCarNote(lineObj.getString("r_car"));
       		remark.setGuideNote(lineObj.getString("r_guide"));
       		remark.setInsuranceNote(lineObj.getString("r_insure"));
       		remark.setAppointRule(lineObj.getString("r_booking_rule"));
       		remark.setRemarkInfo(lineObj.getString("r_other"));
       		int id=productInfoBiz.insertSelective(info);
       		// product_remark表
       		remark.setProductId(id);
       		productRemarkBiz.saveProductRemark(remark);
       		for(int a=0;a<info.getTravelDays();a++){
           		route =new ProductRoute();
           		route.setProductId(id);
           		route.setDayNum(a+1);
           		route.setBreakfast("");
           		route.setLunch("");
           		route.setSupper("");
           		route.setHotelId(0);
           		route.setHotelName("");
           		route.setRouteDesp("");
           		route.setRouteTip("");
           		route.setRouteShort("");
       			productRouteBiz.saveProductRoute1(route);
       		}   	
   			}
       		}
		return null;
	}

	@Override
	public ResultSupport updateProductSysId(Integer productId,
			Integer productSysId) {
		ResultSupport resultSupport = new ResultSupport();
		productInfoBiz.updateProductSysId(productId, productSysId);
		return resultSupport;
	}

	@Override
	public PageBean productAYList(PageBean pageBean, Map paramMap) {
		PageBean bean = productInfoBiz.selectProductListPage(pageBean, paramMap);
		return bean;
	}

	@Override
	public ResultSupport updateProductInfo(ProductInfo productInfo) {
		ResultSupport resultSupport = new ResultSupport();
		int updateResult = productInfoBiz.updateProductInfo(productInfo);
		if (updateResult < 1) {
			resultSupport.setErrorCode(ProductErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ProductInfoResult viewRoute(Integer productId) {
		ProductInfoResult result = new ProductInfoResult();
		List<ProductRoute> list = productRouteBiz.findProductRouteByProductId(Integer.valueOf(productId));
		result.setProductRoutes(list);
        if(!CollectionUtils.isEmpty(list)){
            ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(Integer.valueOf(productId));
            result.setProductRemark(productRemark);
        }
	        
		return result;
	}

	@Override
	public boolean saveProductRoute(ProductRouteVo productRouteVo) {
		boolean result = productRouteBiz.saveProductRoute(productRouteVo);
		return result;
	}

	@Override
	public boolean editProductRoute(ProductRouteVo productRouteVo) {

		boolean result = productRouteBiz.editProductRoute(productRouteVo);
		return result;
	}

}

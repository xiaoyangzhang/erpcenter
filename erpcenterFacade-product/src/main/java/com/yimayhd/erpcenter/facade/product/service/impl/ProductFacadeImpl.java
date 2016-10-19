package com.yimayhd.erpcenter.facade.product.service.impl;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductTagBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.constans.Constants;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductTag;
import com.yimayhd.erpcenter.dal.product.vo.DictWithSelectInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ProductListParam;
import com.yimayhd.erpcenter.facade.query.ProductRemarkDTO;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
import com.yimayhd.erpcenter.facade.query.ProductTagDTO;
import com.yimayhd.erpcenter.facade.result.GetProductRouteResult;
import com.yimayhd.erpcenter.facade.result.ProductInfoResult;
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
	
	@Override
	public int saveBasicInfo(ProductSaveDTO productSaveDTO) {
		if(null == productSaveDTO || null == productSaveDTO.getProductInfoVo()){
			return -1;
		}
		ProductInfoVo productInfoVo = productSaveDTO.getProductInfoVo();
		if (productInfoVo.getProductInfo().getId() == null) {
			productInfoVo.getProductInfo().setCreatorId(productSaveDTO.getCreateId());
			productInfoVo.getProductInfo().setCreatorName(productSaveDTO.getCreateName());
			productInfoVo.getProductInfo().setBizId(productSaveDTO.getBizId());
			productInfoVo.setOrgIdSet(productInfoVo.getOrgIdSet());
		}
		String brandCode = dicBiz.getById(productInfoVo.getProductInfo().getBrandId().toString()).getCode();
		int id = productInfoBiz.saveProductInfo(productInfoVo, productSaveDTO.getBizCode(), brandCode);
		boolean result = false;
		if (productInfoVo.getProductInfo().getId() == null) {
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
		ToProductTagResult ToProductTagResult = new ToProductTagResult();
		
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
        ToProductTagResult.setLineThemeListPlus(lineThemeListPlus);

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
        ToProductTagResult.setLineLevelListPlus(lineLevelListPlus);

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
        ToProductTagResult.setAttendMethodListPlus(attendMethodListPlus);

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
        ToProductTagResult.setHotelLevelListPlus(hotelLevelListPlus);

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
        ToProductTagResult.setDaysPeriodListPlus(daysPeriodListPlus);
        
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
        ToProductTagResult.setPriceRangeListPlus(priceRangeListPlus);
       
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
        ToProductTagResult.setExitDestinationListPlus(exitDestinationListPlus);
       
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
        ToProductTagResult.setDomesticDestinationListPlus(domesticDestinationListPlus);
        
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
        ToProductTagResult.setTypeListPlus(typeListPlus);
        
		return ToProductTagResult;
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
	public ResultSupport deleteProduct(int productId, byte state) {
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
				result.setErrorCode(ProductErrorCode.DEL_ERROR);
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

}

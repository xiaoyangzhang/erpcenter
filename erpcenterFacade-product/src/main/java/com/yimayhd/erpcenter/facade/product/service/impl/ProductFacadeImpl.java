package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductTagBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.facade.errorcode.ProductErrorCode;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
import com.yimayhd.erpcenter.facade.query.ProductTagDTO;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
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
		int id = productInfoBiz.saveProductInfo(productInfoVo, productSaveDTO.getBizCode(), productSaveDTO.getBrandCode());
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
			if (pageBeanResult == null ) {
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

}

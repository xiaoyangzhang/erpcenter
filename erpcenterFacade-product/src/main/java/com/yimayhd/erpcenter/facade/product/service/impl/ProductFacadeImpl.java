package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
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
	
	@Autowired
	private ProductInfoBiz productInfoBiz;

	@Autowired
	private ProductRouteBiz productRouteBiz;

	/**
	 * 保存产品基本信息
	 */
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
	public PageBean selectProductList(PageBean pageBean,
			Map<String, Object> parameters) {
		return null;
	}

}

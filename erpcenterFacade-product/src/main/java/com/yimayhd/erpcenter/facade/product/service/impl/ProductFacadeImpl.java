package com.yimayhd.erpcenter.facade.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.facade.service.ProductFacade;
import com.yimayhd.erpcenter.service.ProductService;

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
	private ProductService productBiz;
	/**
	 * 保存产品基本信息
	 */
	@Override
	public int saveBasicInfo(ProductInfoVo productInfoVo, String bizCode,
			String brandCode) {
		return productBiz.saveBasicInfo(productInfoVo, bizCode, brandCode);
	}

}

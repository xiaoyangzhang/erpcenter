package com.yimayhd.erpcenter.facade.product.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
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
	/**
	 * 保存产品基本信息
	 */
	@Override
	public int saveBasicInfo(ProductInfoVo productInfoVo, String bizCode,
			String brandCode) {
				return 0;
		//return productBiz.saveBasicInfo(productInfoVo, bizCode, brandCode);
	}
	
	@Override
	public PageBean selectProductList(PageBean pageBean,
			Map<String, Object> parameters) {
		return null;
	}

}

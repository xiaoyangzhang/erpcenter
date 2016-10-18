package com.yimayhd.erpcenter.facade.service;

import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;


/**
 * 
* @ClassName: ProductFacade 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface ProductFacade {
	int saveBasicInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);
}

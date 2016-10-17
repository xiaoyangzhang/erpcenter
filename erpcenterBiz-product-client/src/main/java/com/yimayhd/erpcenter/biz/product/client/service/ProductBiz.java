package com.yimayhd.erpcenter.biz.product.client.service;

import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;



/**
 * 
* @ClassName: ProductBiz 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface ProductBiz {
	int saveBasicInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);
}

package com.yimayhd.erpcenter.service;

import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;



/**
 * 
* @ClassName: ProductService 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午6:32:01 
*
 */
public interface ProductService {
	int saveBasicInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);
}

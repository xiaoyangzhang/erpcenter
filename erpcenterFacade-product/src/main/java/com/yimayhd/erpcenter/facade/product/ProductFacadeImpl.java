package com.yimayhd.erpcenter.facade.product;

import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.facade.client.ProductFacade;

/**
 * 
* @ClassName: ProductFacadeImpl 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:51:55 
*
 */
public class ProductFacadeImpl implements ProductFacade{
	
	/**
	 * 保存产品基本信息
	 */
	@Override
	public int saveBasicInfo(ProductInfoVo productInfoVo, String bizCode,
			String brandCode) {
		
		return 0;
	}

}

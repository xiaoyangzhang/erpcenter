package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.service.ProductDal;



/**
 * 
* @ClassName: ProductBizImpl 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:51:55 
*
 */
public class ProductDalImpl implements ProductDal{
	
	/**
	 * 保存产品基本信息
	 */
	@Override
	public int saveBasicInfo(ProductInfoVo productInfoVo, String bizCode,
			String brandCode) {
		
		return 0;
	}

	
	@Override
	public List<ProductInfo> selectProductListPage(PageBean pageBean,
			Map<String, Object> parameters) {
		return null;
	}

}

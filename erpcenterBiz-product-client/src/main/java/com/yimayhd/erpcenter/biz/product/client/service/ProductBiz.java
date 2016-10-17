package com.yimayhd.erpcenter.biz.product.client.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;

import java.util.Map;


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

	/**
	 * 分页查询产品列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> pageBean,Map parameters);
}

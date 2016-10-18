package com.yimayhd.erpcenter.dal.product.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;

import java.util.List;
import java.util.Map;


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
	/**
	 * 分页查询产品列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> pageBean,Map parameters);
	/**
	 * 修改产品
	 * @param productInfo
	 * @return
	 */
	int updateProductInfo(ProductInfo productInfo);

	/**
	 *
	 * @param productInfo
	 * @return
	 */
	List<ProductRoute> findProductRouteByProductId(Integer productId);
}

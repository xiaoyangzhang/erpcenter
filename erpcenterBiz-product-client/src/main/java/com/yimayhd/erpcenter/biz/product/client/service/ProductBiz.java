package com.yimayhd.erpcenter.biz.product.client.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
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
	/**
	 * 保存产品基本信息
	 * @param productInfoVo
	 * @param bizCode
	 * @param brandCode
	 * @return
	 */
	int saveBasicInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);

	/**
	 * 分页查询产品列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> pageBean,Map parameters);
	
	
	int updateProductInfo(ProductInfo productInfo);
	
	List<ProductRoute> findProductRouteByProductId(Integer productId);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:获取产品列表
	* @param 
	* @return List<ProductInfo>
	* @throws
	 */
	List<ProductInfo> selectProductListPage(PageBean pageBean,Map<String, Object> parameters);
}

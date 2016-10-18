package com.yimayhd.erpcenter.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;



/**
 * 
* @ClassName: ProductService 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午6:32:01 
*
 */
public interface ProductDal {
	int saveBasicInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:产品列表
	* @param 
	* @return List<ProductInfo>
	* @throws
	 */
	List<ProductInfo> selectProductListPage(PageBean pageBean,Map<String, Object> parameters);
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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	ProductInfo findProductInfoById(Integer id);
}

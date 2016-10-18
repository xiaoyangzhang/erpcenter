package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
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


	/* (non-Javadoc)
	 * <p>Title: findProductInfos</p> 
	 * <p>Description: </p> 
	 * @param pageBean
	 * @param parameters
	 * @return 
	 * @see com.yimayhd.erpcenter.service.ProductDal#findProductInfos(com.yihg.mybatis.utility.PageBean, java.util.Map)
	 */
	@Override
	public PageBean<ProductInfo> findProductInfos(
			PageBean<ProductInfo> pageBean, Map parameters) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * <p>Title: updateProductInfo</p> 
	 * <p>Description: </p> 
	 * @param productInfo
	 * @return 
	 * @see com.yimayhd.erpcenter.service.ProductDal#updateProductInfo(com.yimayhd.erpcenter.dal.product.po.ProductInfo)
	 */
	@Override
	public int updateProductInfo(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * <p>Title: findProductRouteByProductId</p> 
	 * <p>Description: </p> 
	 * @param productId
	 * @return 
	 * @see com.yimayhd.erpcenter.service.ProductDal#findProductRouteByProductId(java.lang.Integer)
	 */
	@Override
	public List<ProductRoute> findProductRouteByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

}

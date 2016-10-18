package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.client.service.ProductBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;


/**
 * 
* @ClassName: ProductBizImpl 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:51:55 
*
 */
public class ProductBizImpl implements ProductBiz{
	
	/**
	 * 保存产品基本信息
	 */
	@Override
	public int saveBasicInfo(ProductInfoVo productInfoVo, String bizCode,
			String brandCode) {
		
		return 0;
	}

	/**
	 * 分页查询产品列表
	 *
	 * @param pageBean
	 * @param parameters
	 * @return
	 */
	@Override
	public PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> pageBean, Map parameters) {
		return null;
	}

	/**
	 * 修改产品
	 * @param productInfo
	 * @return
	 */
	@Override
	public int updateProductInfo(ProductInfo productInfo) {
		return 0;
	}

	/**
	 * 查询 产品-路线
	 * @param productId
	 * @return
	 */
	@Override
	public List<ProductRoute> findProductRouteByProductId(Integer productId) {
		return null;
	}


	
	@Override
	public List<ProductInfo> selectProductListPage(PageBean pageBean,
			Map<String, Object> parameters) {
		return null;
	}

}

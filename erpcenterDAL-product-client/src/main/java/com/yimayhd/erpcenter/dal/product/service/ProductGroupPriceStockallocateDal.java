package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupPriceStockallocate;


/**
 * @author : xuzejun
 * @date : 2015年7月2日 下午3:58:56
 * @Description: 分配人数
 */
public interface ProductGroupPriceStockallocateDal {
	/**
	 * 修改
	 */
	//int updateByPrimaryKeySelective(ProductGroupPriceStockallocate record);
	
	/**
	 * 删除
	 */
	//int deleteByPrimaryKey(Integer id);
	
	/**
	 * 根据price_id查询
	 */
	 List<ProductGroupPriceStockallocate> selectByPrimaryKey(Integer priceId);
	
}

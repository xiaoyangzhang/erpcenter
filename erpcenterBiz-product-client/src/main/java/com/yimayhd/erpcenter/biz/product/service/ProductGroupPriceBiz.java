package com.yimayhd.erpcenter.biz.product.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.vo.PriceCopyVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductPriceVo;

/**
 * @author : xuzejun
 * @date : 2015年7月2日 下午3:58:56
 * @Description: 价格组接口
 */
public interface ProductGroupPriceBiz {
	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int save(ProductPriceVo priceVo);
	int save(ProductGroupPrice price);
	//int save2(ProductGroupPrice price);

	boolean delete(Integer priceId);
	
	List<ProductGroupPrice> selectProductGroupPrices(Integer groupId, String year, String month);
	
	ProductPriceVo selectByPrimaryKey(Integer id);

	/**
	 * edited by ge 20151027
	 * 如果是统一定价则supplierId传入null即可
	 * 如果是普通模式分别定价，则传入地接社SupplierId
	 * @param priceId
	 * @param supplierId
	 * @param increaseCount
	 * @return
	 */
	boolean updateStock(Integer priceId, Integer supplierId, Integer increaseCount);
	
	int copyGroupPrice(PriceCopyVo copyVo);
	Integer selectProductByProduct(Map parameters);
	
	List<Map> getMinPriceByProductIdSetAndDate(List<Integer> productIds,Date date);
	
	List<ProductGroupPrice> selectPriceByGroupId(Integer groupId);
	void batchInsertPriceGroup(Integer bizId, String json);
	
	List<ProductGroupPrice> getPriceByPidAndUserIdAndDate(Integer bizId,Integer productId,Integer operatorId,Date date);
}

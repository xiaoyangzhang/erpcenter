package com.yimayhd.erpcenter.dal.sales.client.operation.service;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface BookingShopDetailDeployService {
	int deleteByPrimaryKey(Integer bdId);

	List<BookingShopDetailDeploy> selectByDetailId(Integer dId);

	int insertSelective(BookingShopDetailDeployVO record);
	
	int saveSelective(BookingShopDetailDeploy record);
	
	int updateByPrimaryKeySelective(BookingShopDetailDeploy record);

	List<BookingShopDetailDeploy> selectByBookingId(Integer dId);
	void updateBookingShopDetailDeploy(BookingShopDetailDeployVO deploy);
	int selectBuyTotalByProduct(Map map);
	/**
	 * 删除掉该购物店客人购物数据信息
	 * @param shopId
	 * @return
	 */
	int deleteByShopId(Integer shopId);
	
	/**
	 * 判断购物店的客人购物录入数据记录数
	 * @param shopId
	 * @return
	 */
	int getCountByShopId(Integer shopId);

	BigDecimal getSumBuyTotalByBookingId(Integer id);
}

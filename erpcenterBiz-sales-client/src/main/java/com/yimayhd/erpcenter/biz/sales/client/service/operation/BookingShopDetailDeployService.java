package com.yimayhd.erpcenter.biz.sales.client.service.operation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yihg.operation.po.BookingShopDetailDeploy;
import com.yihg.operation.vo.BookingShopDetailDeployVO;

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

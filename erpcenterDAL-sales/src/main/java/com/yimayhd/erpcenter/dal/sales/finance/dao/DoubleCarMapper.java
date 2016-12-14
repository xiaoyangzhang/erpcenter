package com.yimayhd.erpcenter.dal.sales.finance.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.car.po.HotelMsg;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPortHotel;

public interface DoubleCarMapper {

	List<TransPort> selectTransportByOrderId(int orderId);
	
	List<TransPort> selectTransportByOrderIds(@Param("orderIds") String orderIds);

	List<TransPortHotel> selectTransPortHotelByGroupIds(@Param("groupIds") String groupIds);
	
	List<BookingDeliveryPrice> selectDeliveryPriceListPage(@Param("page") PageBean pageBean);
	
	List<GroupOrderGuest> selectGroupOrderGuestListPage(@Param("page") PageBean pageBean);

	List<HotelMsg> synHotelMsg(@Param("groupId") int groupId, @Param("date") Date date);
	
}

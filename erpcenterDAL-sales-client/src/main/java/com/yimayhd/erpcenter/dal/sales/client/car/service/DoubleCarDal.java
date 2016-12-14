package com.yimayhd.erpcenter.dal.sales.client.car.service;

import java.sql.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.car.po.HotelMsg;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;

public interface DoubleCarDal {
	List<TransPort> selectTransportByOrderId(int orderId);

	List<TransPort> selectTransportByOrderIds(String orderIds);
	
	List<BookingDeliveryPrice> selectDeliveryPrice(String orderIds, int page, int pageSize);
	
	List<GroupOrderGuest> selectOrderGuestListPage(String orderIds, int page, int pageSize);

	List<HotelMsg> synHotelMsg(int groupId, int type, Date departureDate,
			Date arrivalDate);

}

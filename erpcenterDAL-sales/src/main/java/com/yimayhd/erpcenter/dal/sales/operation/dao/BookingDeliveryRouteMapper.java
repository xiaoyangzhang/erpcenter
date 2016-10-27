package com.yimayhd.erpcenter.dal.sales.operation.dao;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryRoute;

import java.util.List;


public interface BookingDeliveryRouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingDeliveryRoute record);

    int insertSelective(BookingDeliveryRoute record);

    BookingDeliveryRoute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingDeliveryRoute record);

    int updateByPrimaryKey(BookingDeliveryRoute record);
    
    int insertBatch(List<BookingDeliveryRoute> list);
    
    int deleteBatch(List<BookingDeliveryRoute> list);

	List<BookingDeliveryRoute> getRouteListByBookingId(Integer bookingId);
	
	int deleteByBookingId(Integer bookingId);
}
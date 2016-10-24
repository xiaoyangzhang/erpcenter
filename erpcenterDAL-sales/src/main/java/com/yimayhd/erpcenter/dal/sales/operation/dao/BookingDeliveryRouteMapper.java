package com.yihg.operation.dao;

import java.util.List;

import com.yihg.operation.po.BookingDeliveryRoute;

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
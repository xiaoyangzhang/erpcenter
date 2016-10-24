package com.yihg.operation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.operation.po.BookingDeliveryOrder;

public interface BookingDeliveryOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingDeliveryOrder record);

    int insertSelective(BookingDeliveryOrder record);

    BookingDeliveryOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingDeliveryOrder record);

    int updateByPrimaryKey(BookingDeliveryOrder record);
    
    int insertBatch(List<BookingDeliveryOrder> list);    
    
    int deleteBatch(List<BookingDeliveryOrder> list);

	List<BookingDeliveryOrder> getOrderListByBookingId(Integer bookingId);
	
	int deleteByBookingId(Integer bookingId);
	
}
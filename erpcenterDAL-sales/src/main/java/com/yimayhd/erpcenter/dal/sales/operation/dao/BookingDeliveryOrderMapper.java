package com.yimayhd.erpcenter.dal.sales.operation.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;
import org.apache.ibatis.annotations.Param;


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
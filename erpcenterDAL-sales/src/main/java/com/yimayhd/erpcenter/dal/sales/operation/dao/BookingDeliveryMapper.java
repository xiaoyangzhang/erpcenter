package com.yimayhd.erpcenter.dal.sales.operation.dao;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingDeliveryStatics;
import org.apache.ibatis.annotations.Param;


public interface BookingDeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingDelivery record);

    int insertSelective(BookingDelivery record);

    BookingDelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingDelivery record);

    int updateByPrimaryKey(BookingDelivery record);
    
    BookingDeliveryStatics getStaticsByGroupId(Integer groupId);
    
    List<BookingDelivery> getDeliveryListByGroupId(Integer groupId);
    List<BookingDelivery> getDeliveryListByGroupIdAndName(@Param("groupId")Integer groupId,@Param("supplierName")String supplierName);
    
    int updateTotal(Integer id);
   // BookingDelivery getBookingDeliveryByOrderId(@Param("orderId")Integer orderId);
    
    /**********团人数更新后更新下接订单的人数**************/
    List<Map<String,Integer>> getBookingIdListByGroupId(@Param("groupId")Integer groupId);
    Map<String,Object> getGuestCountByBookingId(@Param("bookingId")Integer bookingId);
    int updateGuestCntByGroupId(BookingDelivery record);

	List<BookingDelivery> selectInitDeliveryList(Integer groupId);
}
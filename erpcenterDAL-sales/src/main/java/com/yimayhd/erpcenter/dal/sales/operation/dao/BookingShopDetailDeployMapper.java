package com.yihg.operation.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;




import com.yihg.operation.po.BookingShopDetailDeploy;

public interface BookingShopDetailDeployMapper {
    int deleteByPrimaryKey(Integer bdId);

    int insert(BookingShopDetailDeploy record);

    int insertSelective(BookingShopDetailDeploy record);

    List<BookingShopDetailDeploy> selectByDetailId(Integer dId);
    List<BookingShopDetailDeploy> selectByBookingId(Integer dId);
    
    int updateByPrimaryKeySelective(BookingShopDetailDeploy record);

    int updateByPrimaryKey(BookingShopDetailDeploy record);
    Integer selectBuyTotalByProduct(@Param("parameter")Map map);
    
    List<BookingShopDetailDeploy> getByBookingIdAndOrderId(@Param("bookingId")Integer bookingId,@Param("orderId")Integer orderId);
    
    int deleteByBookingIdAndOrderId(@Param("bookingId")Integer bookingId,@Param("orderId")Integer orderId);
    
    int deleteByBookingId(@Param("bookingId")Integer bookingId);
    
    int getCountByBookingId(@Param("bookingId")Integer bookingId);

	BigDecimal getSumBuyTotalByBookingId(@Param("bookingId")Integer bookingId);
}
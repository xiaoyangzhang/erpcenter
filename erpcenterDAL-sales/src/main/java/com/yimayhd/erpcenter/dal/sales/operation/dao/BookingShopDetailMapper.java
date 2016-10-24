package com.yimayhd.erpcenter.dal.sales.operation.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import org.apache.ibatis.annotations.Param;

public interface BookingShopDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingShopDetail record);

    int insertSelective(BookingShopDetail record);

    BookingShopDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingShopDetail record);
    int updateByBookingId(BookingShopDetail record);

    int updateByPrimaryKey(BookingShopDetail record);

	List<BookingShopDetail> getShopDetailListByBookingId(Integer bId);
	void deleteByBookingId(@Param("bookingId") Integer bookingId);
	
}
package com.yihg.operation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.po.BookingSupplierDetail;

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
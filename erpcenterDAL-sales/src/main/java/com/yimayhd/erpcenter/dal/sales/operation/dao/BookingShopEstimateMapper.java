package com.yihg.operation.dao;

import com.yihg.operation.po.BookingShopEstimate;

public interface BookingShopEstimateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingShopEstimate record);

    int insertSelective(BookingShopEstimate record);

    BookingShopEstimate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingShopEstimate record);

    int updateByPrimaryKey(BookingShopEstimate record);
}
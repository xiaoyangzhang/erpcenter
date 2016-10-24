package com.yihg.operation.dao;

import com.yihg.operation.po.BookingDeliverySupplier;

public interface BookingDeliverySupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingDeliverySupplier record);

    int insertSelective(BookingDeliverySupplier record);

    BookingDeliverySupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingDeliverySupplier record);

    int updateByPrimaryKey(BookingDeliverySupplier record);
}
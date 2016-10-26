package com.yimayhd.erpcenter.dal.sales.operation.dao;


import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliverySupplier;

public interface BookingDeliverySupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingDeliverySupplier record);

    int insertSelective(BookingDeliverySupplier record);

    BookingDeliverySupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingDeliverySupplier record);

    int updateByPrimaryKey(BookingDeliverySupplier record);
}
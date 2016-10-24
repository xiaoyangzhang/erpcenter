package com.yimayhd.erpcenter.dal.sales.operation.dao;


import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuideSheet;

public interface BookingGuideSheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingGuideSheet record);

    int insertSelective(BookingGuideSheet record);

    BookingGuideSheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingGuideSheet record);

    int updateByPrimaryKey(BookingGuideSheet record);
}
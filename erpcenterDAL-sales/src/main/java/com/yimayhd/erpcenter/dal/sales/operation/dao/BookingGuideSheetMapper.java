package com.yihg.operation.dao;

import com.yihg.operation.po.BookingGuideSheet;

public interface BookingGuideSheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingGuideSheet record);

    int insertSelective(BookingGuideSheet record);

    BookingGuideSheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingGuideSheet record);

    int updateByPrimaryKey(BookingGuideSheet record);
}
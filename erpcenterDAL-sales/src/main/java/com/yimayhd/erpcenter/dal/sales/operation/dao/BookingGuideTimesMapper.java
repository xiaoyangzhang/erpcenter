package com.yihg.operation.dao;

import java.util.List;

import com.yihg.operation.po.BookingGuideTimes;

public interface BookingGuideTimesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingGuideTimes record);

    int insertSelective(BookingGuideTimes record);

    BookingGuideTimes selectByPrimaryKey(Integer id);
    List<BookingGuideTimes> selectListByGroupId(Integer groupId);
    int updateByPrimaryKeySelective(BookingGuideTimes record);

    int updateByPrimaryKey(BookingGuideTimes record);

	int deleteByBookingId(Integer bookingId);
}
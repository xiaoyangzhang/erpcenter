package com.yimayhd.erpcenter.dal.sales.operation.dao;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuideTimes;

import java.util.List;


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
package com.yimayhd.erpcenter.dal.sales.query.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.BookingOrderDTO;



public interface BookingMapper {
	
	List<BookingOrderDTO> selectBookingOrders(@Param("page") PageBean pageBean);
}

package com.yimayhd.erpcenter.dal.sales.query.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.query.service.BookingDal;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.BookingOrderDTO;
import com.yimayhd.erpcenter.dal.sales.query.dao.BookingMapper;

public class BookingDalImpl implements BookingDal {

	@Autowired
	private BookingMapper bookingMapper;
	@Override
	public PageBean selectBookingOrders(PageBean pageBean) {
		
		List<BookingOrderDTO> bookingOrders = bookingMapper.selectBookingOrders(pageBean);
		pageBean.setResult(bookingOrders);
		return pageBean;
	}

}

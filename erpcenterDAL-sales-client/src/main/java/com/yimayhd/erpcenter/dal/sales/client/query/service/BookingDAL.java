package com.yimayhd.erpcenter.dal.sales.client.query.service;

import com.yihg.mybatis.utility.PageBean;


public interface BookingDAL {
	public PageBean selectBookingOrders(PageBean pageBean);
}

package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;

public class BookingShopInputResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingGroup bookingGroup;
	private PageBean pageBean;
	public BookingGroup getBookingGroup() {
		return bookingGroup;
	}
	public void setBookingGroup(BookingGroup bookingGroup) {
		this.bookingGroup = bookingGroup;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	

}

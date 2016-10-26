package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;

public class LoadShopInfoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingShop bookingShop;
	private List<BookingGuide> bookingGuides;
	private String isEdit;
	public BookingShop getBookingShop() {
		return bookingShop;
	}
	public void setBookingShop(BookingShop bookingShop) {
		this.bookingShop = bookingShop;
	}
	public List<BookingGuide> getBookingGuides() {
		return bookingGuides;
	}
	public void setBookingGuides(List<BookingGuide> bookingGuides) {
		this.bookingGuides = bookingGuides;
	}
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	
	

}

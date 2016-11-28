package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;

public class SaveBookingDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<BookingSupplierDetail> details;
	private BookingSupplier bookingSupplier;
	private String code;
	public List<BookingSupplierDetail> getDetails() {
		return details;
	}
	public void setDetails(List<BookingSupplierDetail> details) {
		this.details = details;
	}
	public BookingSupplier getBookingSupplier() {
		return bookingSupplier;
	}
	public void setBookingSupplier(BookingSupplier bookingSupplier) {
		this.bookingSupplier = bookingSupplier;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

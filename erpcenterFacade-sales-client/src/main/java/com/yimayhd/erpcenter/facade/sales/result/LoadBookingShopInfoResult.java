package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;

public class LoadBookingShopInfoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BookingShop> bookingShops;
	
	private TourGroupPriceAndPersons tourGroupPriceAndPersons;

	public List<BookingShop> getBookingShops() {
		return bookingShops;
	}

	public void setBookingShops(List<BookingShop> bookingShops) {
		this.bookingShops = bookingShops;
	}

	public TourGroupPriceAndPersons getTourGroupPriceAndPersons() {
		return tourGroupPriceAndPersons;
	}

	public void setTourGroupPriceAndPersons(
			TourGroupPriceAndPersons tourGroupPriceAndPersons) {
		this.tourGroupPriceAndPersons = tourGroupPriceAndPersons;
	}
	
	
	
}

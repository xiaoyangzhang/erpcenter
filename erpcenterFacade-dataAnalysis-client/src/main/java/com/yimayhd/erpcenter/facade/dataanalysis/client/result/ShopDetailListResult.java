package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;

public class ShopDetailListResult extends BaseResult {
	private static final long serialVersionUID = 3313118359822171573L;
	private List<BookingShopDetail> bookingShopDetail;

	public List<BookingShopDetail> getBookingShopDetail() {
		return bookingShopDetail;
	}

	public void setBookingShopDetail(List<BookingShopDetail> bookingShopDetail) {
		this.bookingShopDetail = bookingShopDetail;
	}
}

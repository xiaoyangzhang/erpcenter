package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookingDeliveryStatics implements Serializable {
	private Integer bookingCount;
	private BigDecimal bookingTotalPrice;
	public Integer getBookingCount() {
		return bookingCount;
	}
	public void setBookingCount(Integer bookingCount) {
		this.bookingCount = bookingCount;
	}
	public BigDecimal getBookingTotalPrice() {
		return bookingTotalPrice;
	}
	public void setBookingTotalPrice(BigDecimal bookingTotalPrice) {
		this.bookingTotalPrice = bookingTotalPrice;
	}
		
}

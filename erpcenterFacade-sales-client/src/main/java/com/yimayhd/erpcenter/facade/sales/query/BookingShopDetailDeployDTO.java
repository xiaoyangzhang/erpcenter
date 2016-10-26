package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;

public class BookingShopDetailDeployDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingShopDetailDeployVO bookingShopDetailDeployVO;

	public BookingShopDetailDeployVO getBookingShopDetailDeployVO() {
		return bookingShopDetailDeployVO;
	}

	public void setBookingShopDetailDeployVO(
			BookingShopDetailDeployVO bookingShopDetailDeployVO) {
		this.bookingShopDetailDeployVO = bookingShopDetailDeployVO;
	}
	
	

}

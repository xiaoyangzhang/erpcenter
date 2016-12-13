package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;

public class SearchDeliveryPriceResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private List<BookingDeliveryPrice> priceList;

	public List<BookingDeliveryPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<BookingDeliveryPrice> priceList) {
		this.priceList = priceList;
	}
	
}

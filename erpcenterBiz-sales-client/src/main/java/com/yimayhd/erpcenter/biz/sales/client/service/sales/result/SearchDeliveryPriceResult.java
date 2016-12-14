package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;

public class SearchDeliveryPriceResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private List<BookingDeliveryPrice> priceList;
	private long totalCount;
	private long totalPage;

	public List<BookingDeliveryPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<BookingDeliveryPrice> priceList) {
		this.priceList = priceList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
}

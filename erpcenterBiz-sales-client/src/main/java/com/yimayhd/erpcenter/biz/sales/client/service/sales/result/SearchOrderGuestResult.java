package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.GroupOrderGuest;

public class SearchOrderGuestResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private List<GroupOrderGuest> priceList;

	public List<GroupOrderGuest> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<GroupOrderGuest> priceList) {
		this.priceList = priceList;
	}
	
}

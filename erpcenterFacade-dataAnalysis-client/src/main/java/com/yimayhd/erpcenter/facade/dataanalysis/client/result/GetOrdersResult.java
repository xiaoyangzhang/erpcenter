package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class GetOrdersResult extends BaseResult {
	private static final long serialVersionUID = -6643626526267944174L;
	private List<GroupOrder> orders;

	public List<GroupOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}
}

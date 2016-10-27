package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GroupOrderListResult extends BaseStateResult {
	private static final long serialVersionUID = -69783570787723033L;
	private List<GroupOrder> orders;

	public List<GroupOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}
}

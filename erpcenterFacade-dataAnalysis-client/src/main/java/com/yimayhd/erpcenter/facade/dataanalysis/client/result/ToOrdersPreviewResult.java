package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToOrdersPreviewResult extends BaseResult {
	private static final long serialVersionUID = -8139244338093221977L;
	private List<GroupOrder> orders;
	private Map parameters;

	public List<GroupOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}

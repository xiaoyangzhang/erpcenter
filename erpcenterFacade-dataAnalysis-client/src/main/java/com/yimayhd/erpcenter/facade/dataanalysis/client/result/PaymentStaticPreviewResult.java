package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class PaymentStaticPreviewResult extends BaseResult{
	private static final long serialVersionUID = 2830506571565494832L;
	private List<GroupOrder> orders;
	private Map<String, Object> pms;

	public List<GroupOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}

	public Map<String, Object> getPms() {
		return pms;
	}

	public void setPms(Map<String, Object> pms) {
		this.pms = pms;
	}
}

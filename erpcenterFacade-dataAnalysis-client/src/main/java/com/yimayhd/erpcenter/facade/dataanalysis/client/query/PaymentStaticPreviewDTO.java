package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class PaymentStaticPreviewDTO extends BaseDTO {
	private static final long serialVersionUID = -4502292685858621592L;
	private GroupOrder order;
	private Map<String, Object> pms;

	public GroupOrder getOrder() {
		return order;
	}

	public void setOrder(GroupOrder order) {
		this.order = order;
	}

	public Map<String, Object> getPms() {
		return pms;
	}

	public void setPms(Map<String, Object> pms) {
		this.pms = pms;
	}
}

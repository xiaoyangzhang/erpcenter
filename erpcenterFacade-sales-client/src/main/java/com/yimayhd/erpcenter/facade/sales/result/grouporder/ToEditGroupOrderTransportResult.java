package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToEditGroupOrderTransportResult extends BaseStateResult{
	private static final long serialVersionUID = 2403107036726788598L;
	private GroupOrderTransport orderTransport;

	public GroupOrderTransport getOrderTransport() {
		return orderTransport;
	}

	public void setOrderTransport(GroupOrderTransport orderTransport) {
		this.orderTransport = orderTransport;
	}
}

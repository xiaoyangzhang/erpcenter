package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;

public class AddGroupOrderTransportDTO implements Serializable {
	private static final long serialVersionUID = -1776614481224739783L;
	private GroupOrderTransport groupOrderTransport;

	public GroupOrderTransport getGroupOrderTransport() {
		return groupOrderTransport;
	}

	public void setGroupOrderTransport(GroupOrderTransport groupOrderTransport) {
		this.groupOrderTransport = groupOrderTransport;
	}
}

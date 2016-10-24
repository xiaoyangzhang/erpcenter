package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.GroupOrderTransport;
import com.yihg.sales.po.GroupRequirement;

public class TransportVO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private List<GroupOrderTransport> groupOrderTransportList;

	public List<GroupOrderTransport> getGroupOrderTransportList() {
		return groupOrderTransportList;
	}

	public void setGroupOrderTransportList(
			List<GroupOrderTransport> groupOrderTransportList) {
		this.groupOrderTransportList = groupOrderTransportList;
	}




}

package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;

public class GroupOrderTransportVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<GroupOrderTransport> groupOrderTransportList;

	public List<GroupOrderTransport> getGroupOrderTransportList() {
		return groupOrderTransportList;
	}

	public void setGroupOrderTransportList(
			List<GroupOrderTransport> groupOrderTransportList) {
		this.groupOrderTransportList = groupOrderTransportList;
	}
	
}

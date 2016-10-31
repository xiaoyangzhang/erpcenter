package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class EditGroupOrderDTO implements Serializable {
	private static final long serialVersionUID = 4218802900844968480L;
	private GroupOrder groupOrder;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
}

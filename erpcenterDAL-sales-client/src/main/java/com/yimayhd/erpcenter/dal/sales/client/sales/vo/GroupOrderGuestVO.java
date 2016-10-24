package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.GroupOrderGuest;

public class GroupOrderGuestVO implements Serializable {
	private List<GroupOrderGuest>  groupOrderGuestList;

	public List<GroupOrderGuest> getGroupOrderGuestList() {
		return groupOrderGuestList;
	}

	public void setGroupOrderGuestList(List<GroupOrderGuest> groupOrderGuestList) {
		this.groupOrderGuestList = groupOrderGuestList;
	}
	
	
}

package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;

public class EditGroupGuestDTO implements Serializable {
	private static final long serialVersionUID = 7586656599560466414L;
	private GroupOrderGuest groupGuest;

	public GroupOrderGuest getGroupGuest() {
		return groupGuest;
	}

	public void setGroupGuest(GroupOrderGuest groupGuest) {
		this.groupGuest = groupGuest;
	}
}

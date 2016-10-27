package com.yimayhd.erpcenter.facade.sales.query.grouprequirement;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;

public class SaveGroupRequirementDTO implements Serializable {
	private static final long serialVersionUID = 7073360194099034232L;
	private GroupRequirement groupRequirement;

	public GroupRequirement getGroupRequirement() {
		return groupRequirement;
	}

	public void setGroupRequirement(GroupRequirement groupRequirement) {
		this.groupRequirement = groupRequirement;
	}
}

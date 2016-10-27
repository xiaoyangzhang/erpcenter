package com.yimayhd.erpcenter.facade.sales.result.grouprequirement;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class EditGroupRequirementResult extends BaseStateResult {
	private static final long serialVersionUID = -8042869333612884645L;
	private GroupRequirement groupRequirement;

	public GroupRequirement getGroupRequirement() {
		return groupRequirement;
	}

	public void setGroupRequirement(GroupRequirement groupRequirement) {
		this.groupRequirement = groupRequirement;
	}
}

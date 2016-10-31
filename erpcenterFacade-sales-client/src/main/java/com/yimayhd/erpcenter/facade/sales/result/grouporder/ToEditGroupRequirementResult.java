package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToEditGroupRequirementResult extends BaseStateResult {
	private static final long serialVersionUID = 3678842274340253145L;
	private GroupRequirement requirement;

	public GroupRequirement getRequirement() {
		return requirement;
	}

	public void setRequirement(GroupRequirement requirement) {
		this.requirement = requirement;
	}
}

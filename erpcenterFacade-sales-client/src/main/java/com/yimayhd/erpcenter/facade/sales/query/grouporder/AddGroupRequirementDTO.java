package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;

public class AddGroupRequirementDTO implements Serializable {
	private static final long serialVersionUID = 3974234332689945923L;
	private GroupRequirement groupRequirement;
	private Integer userId;
	private String userName;

	public GroupRequirement getGroupRequirement() {
		return groupRequirement;
	}

	public void setGroupRequirement(GroupRequirement groupRequirement) {
		this.groupRequirement = groupRequirement;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

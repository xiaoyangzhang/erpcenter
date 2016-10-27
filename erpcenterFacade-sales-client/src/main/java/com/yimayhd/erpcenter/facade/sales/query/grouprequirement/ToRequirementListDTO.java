package com.yimayhd.erpcenter.facade.sales.query.grouprequirement;

import java.io.Serializable;

public class ToRequirementListDTO implements Serializable {
	private static final long serialVersionUID = -962309725007400548L;
	private Integer groupId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
}

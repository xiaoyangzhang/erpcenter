package com.yimayhd.erpcenter.dal.sales.client.tj.po;

import java.io.Serializable;

public class TJGroupQueryId implements Serializable{

	private static final long serialVersionUID = 1488679670956659775L;
	
	private Integer groupId;
	private Integer bizId;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
}
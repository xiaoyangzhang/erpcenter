package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class AddCommission2DTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer groupId;
	private Integer guideId;
	private Integer bizId;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	
	
	
}

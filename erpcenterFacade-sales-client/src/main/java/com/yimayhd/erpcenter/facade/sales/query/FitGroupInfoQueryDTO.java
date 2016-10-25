package com.yimayhd.erpcenter.facade.sales.query;

public class FitGroupInfoQueryDTO {
	
	private Integer groupId;

	private Integer curBizId;
	
	private String TypeCode;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCurBizId() {
		return curBizId;
	}

	public void setCurBizId(Integer curBizId) {
		this.curBizId = curBizId;
	}

	public String getTypeCode() {
		return TypeCode;
	}

	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}
}

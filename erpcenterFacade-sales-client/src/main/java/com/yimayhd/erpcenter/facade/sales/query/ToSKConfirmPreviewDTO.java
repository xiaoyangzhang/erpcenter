package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

public class ToSKConfirmPreviewDTO implements Serializable {

	private Integer groupId;
	private Integer curUserId;
	private Integer supplierId;
	private Integer curBizId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Integer curUserId) {
		this.curUserId = curUserId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getCurBizId() {
		return curBizId;
	}

	public void setCurBizId(Integer curBizId) {
		this.curBizId = curBizId;
	}
}

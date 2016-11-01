package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseDTO implements Serializable {
	private static final long serialVersionUID = 4814254746752941620L;
	private Integer bizId;
	private Set<Integer> userIdSet;

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Set<Integer> getUserIdSet() {
		return userIdSet;
	}

	public void setUserIdSet(Set<Integer> userIdSet) {
		this.userIdSet = userIdSet;
	}
}

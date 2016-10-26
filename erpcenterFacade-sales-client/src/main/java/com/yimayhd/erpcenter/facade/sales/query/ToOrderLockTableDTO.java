package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToOrderLockTableDTO implements Serializable {
	private Integer bizId;
	private Set<Integer> userIdSet;
	private GroupOrder order;

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

	public GroupOrder getOrder() {
		return order;
	}

	public void setOrder(GroupOrder order) {
		this.order = order;
	}
}

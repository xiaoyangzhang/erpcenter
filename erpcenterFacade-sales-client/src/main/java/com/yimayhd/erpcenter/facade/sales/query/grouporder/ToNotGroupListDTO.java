package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToNotGroupListDTO implements Serializable {
	private static final long serialVersionUID = -4055249708771503982L;
	private GroupOrder groupOrder;
	private Integer bizId;
	private Set<Integer> userIdSet;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

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

package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class GetFitOrdersDataDTO implements Serializable {
	private static final long serialVersionUID = 335323577243871907L;
	private GroupOrder groupOrder;
	private Integer curBizId;
	private Set<Integer> userIdSet;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public Integer getCurBizId() {
		return curBizId;
	}

	public void setCurBizId(Integer curBizId) {
		this.curBizId = curBizId;
	}

	public Set<Integer> getUserIdSet() {
		return userIdSet;
	}

	public void setUserIdSet(Set<Integer> userIdSet) {
		this.userIdSet = userIdSet;
	}
}

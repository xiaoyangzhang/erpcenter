package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToProductOrdersTableDTO implements Serializable {
	private static final long serialVersionUID = -8202968183868767111L;
	private GroupOrder order;
	private Integer curBizId;
	private Set<Integer> userIdSet;

	public GroupOrder getOrder() {
		return order;
	}

	public void setOrder(GroupOrder order) {
		this.order = order;
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

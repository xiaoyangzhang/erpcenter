package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;

public class AddGroupOrderPriceDTO implements Serializable {
	private static final long serialVersionUID = -1414059875463204495L;
	private GroupOrderPrice groupOrderPrice;
	private Integer userId;
	private String userName;

	public GroupOrderPrice getGroupOrderPrice() {
		return groupOrderPrice;
	}

	public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
		this.groupOrderPrice = groupOrderPrice;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

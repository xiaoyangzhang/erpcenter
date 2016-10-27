package com.yimayhd.erpcenter.facade.sales.query.budgetitem;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;

public class SaveBudgetItemDTO implements Serializable{

	private static final long serialVersionUID = 1533028810337611970L;
	
	private GroupOrderPrice groupOrderPrice;

	public GroupOrderPrice getGroupOrderPrice() {
		return groupOrderPrice;
	}

	public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
		this.groupOrderPrice = groupOrderPrice;
	}
}

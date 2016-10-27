package com.yimayhd.erpcenter.facade.sales.query.costitem;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;

public class SaveCostItemDTO implements Serializable {
	private static final long serialVersionUID = -3205832493153697628L;
	private GroupOrderPrice groupOrderPrice;

	public GroupOrderPrice getGroupOrderPrice() {
		return groupOrderPrice;
	}

	public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
		this.groupOrderPrice = groupOrderPrice;
	}
}

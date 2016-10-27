package com.yimayhd.erpcenter.facade.sales.result.costitem;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class CostItemResult extends BaseStateResult {
	private static final long serialVersionUID = -1361931041677525975L;
	private GroupOrderPrice groupOrderPrice;

	public GroupOrderPrice getGroupOrderPrice() {
		return groupOrderPrice;
	}

	public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
		this.groupOrderPrice = groupOrderPrice;
	}
}

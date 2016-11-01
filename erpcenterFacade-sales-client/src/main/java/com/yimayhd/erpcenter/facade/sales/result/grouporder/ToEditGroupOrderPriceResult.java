package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToEditGroupOrderPriceResult extends BaseStateResult{
	private static final long serialVersionUID = -9055661666183028019L;
	private GroupOrderPrice groupOrderPrice;

	public GroupOrderPrice getGroupOrderPrice() {
		return groupOrderPrice;
	}

	public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
		this.groupOrderPrice = groupOrderPrice;
	}
}

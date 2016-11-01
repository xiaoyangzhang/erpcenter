package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToEditGroupGuestResult extends BaseStateResult{
	
	private static final long serialVersionUID = -8358033863318604308L;
	private GroupOrderGuest orderGuest;

	public GroupOrderGuest getOrderGuest() {
		return orderGuest;
	}

	public void setOrderGuest(GroupOrderGuest orderGuest) {
		this.orderGuest = orderGuest;
	}
}

package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihg.sales.po.GroupOrderPrice;

public class GroupOrderPriceVO implements Serializable {
	public List<GroupOrderPrice> groupOrderPriceList = new ArrayList<GroupOrderPrice>();

	public List<GroupOrderPrice> getGroupOrderPriceList() {
		return groupOrderPriceList;
	}

	public void setGroupOrderPriceList(List<GroupOrderPrice> groupOrderPriceList) {
		this.groupOrderPriceList = groupOrderPriceList;
	}
	
}

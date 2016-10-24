package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;

public class GroupOrderPriceVO implements Serializable {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 8143631525373910137L;
	public List<GroupOrderPrice> groupOrderPriceList = new ArrayList<GroupOrderPrice>();

	public List<GroupOrderPrice> getGroupOrderPriceList() {
		return groupOrderPriceList;
	}

	public void setGroupOrderPriceList(List<GroupOrderPrice> groupOrderPriceList) {
		this.groupOrderPriceList = groupOrderPriceList;
	}
	
}

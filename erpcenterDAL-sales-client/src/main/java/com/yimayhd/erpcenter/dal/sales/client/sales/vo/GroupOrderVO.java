package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;

public class GroupOrderVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GroupOrder groupOrder;
	public GroupOrderPrice groupOrderPrice;
	public List<GroupOrderGuest> groupOrderGuestList;
	public List<GroupOrderPrice> groupOrderPriceList;

	public GroupOrderVO() {
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public List<GroupOrderGuest> getGroupOrderGuestList() {
		return groupOrderGuestList;
	}

	public void setGroupOrderGuestList(List<GroupOrderGuest> groupOrderGuestList) {
		this.groupOrderGuestList = groupOrderGuestList;
	}

	public List<GroupOrderPrice> getGroupOrderPriceList() {
		return groupOrderPriceList;
	}

	public void setGroupOrderPriceList(List<GroupOrderPrice> groupOrderPriceList) {
		this.groupOrderPriceList = groupOrderPriceList;
	}

	public GroupOrderPrice getGroupOrderPrice() {
		return groupOrderPrice;
	}

	public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
		this.groupOrderPrice = groupOrderPrice;
	}



}

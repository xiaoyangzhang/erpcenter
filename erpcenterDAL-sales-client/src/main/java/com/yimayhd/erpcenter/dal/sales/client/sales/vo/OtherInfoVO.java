package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.GroupOrderGuest;
import com.yihg.sales.po.GroupOrderPrice;
import com.yihg.sales.po.GroupOrderTransport;

public class OtherInfoVO implements Serializable{
	private  List<GroupOrderPrice> groupOrderPriceList;
	private  List<GroupOrderPrice> groupOrderCostList;
	private 	 List<GroupOrderTransport> groupOrderTransportList;
	private List<GroupOrderGuest>  groupOrderGuestList;

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
	public List<GroupOrderTransport> getGroupOrderTransportList() {
		return groupOrderTransportList;
	}
	public void setGroupOrderTransportList(
			List<GroupOrderTransport> groupOrderTransportList) {
		this.groupOrderTransportList = groupOrderTransportList;
	}
	public List<GroupOrderPrice> getGroupOrderCostList() {
		return groupOrderCostList;
	}
	public void setGroupOrderCostList(List<GroupOrderPrice> groupOrderCostList) {
		this.groupOrderCostList = groupOrderCostList;
	}
	
}

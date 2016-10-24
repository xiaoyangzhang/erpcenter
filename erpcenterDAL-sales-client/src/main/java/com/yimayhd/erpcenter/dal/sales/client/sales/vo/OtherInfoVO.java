package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;

public class OtherInfoVO implements Serializable{
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -6762956145615684724L;
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

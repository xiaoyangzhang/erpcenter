package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;

public class SpecialGroupOrderVO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8096864241495950513L;
	private GroupOrder groupOrder;
	private GroupRequirement hotelInfo;
	// 接送信息
	private List<GroupOrderTransport> groupOrderTransportList;
	// 收入
	private List<GroupOrderPrice> groupOrderPriceList;
	// 成本
	private List<GroupOrderPrice> groupOrderCostList;
	// 客人信息
	private List<GroupOrderGuest> groupOrderGuestList;

	// 行程列表
	private List<GroupRouteDayVO> groupRouteDayVOList;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public GroupRequirement getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(GroupRequirement hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	public List<GroupOrderTransport> getGroupOrderTransportList() {
		return groupOrderTransportList;
	}

	public void setGroupOrderTransportList(
			List<GroupOrderTransport> groupOrderTransportList) {
		this.groupOrderTransportList = groupOrderTransportList;
	}

	public List<GroupOrderPrice> getGroupOrderPriceList() {
		return groupOrderPriceList;
	}

	public void setGroupOrderPriceList(List<GroupOrderPrice> groupOrderPriceList) {
		this.groupOrderPriceList = groupOrderPriceList;
	}

	public List<GroupOrderPrice> getGroupOrderCostList() {
		return groupOrderCostList;
	}

	public void setGroupOrderCostList(List<GroupOrderPrice> groupOrderCostList) {
		this.groupOrderCostList = groupOrderCostList;
	}

	public List<GroupOrderGuest> getGroupOrderGuestList() {
		return groupOrderGuestList;
	}

	public void setGroupOrderGuestList(List<GroupOrderGuest> groupOrderGuestList) {
		this.groupOrderGuestList = groupOrderGuestList;
	}

	public List<GroupRouteDayVO> getGroupRouteDayVOList() {
		return groupRouteDayVOList;
	}

	public void setGroupRouteDayVOList(List<GroupRouteDayVO> groupRouteDayVOList) {
		this.groupRouteDayVOList = groupRouteDayVOList;
	}


}

package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.GroupOrder;
import com.yihg.sales.po.GroupOrderGuest;
import com.yihg.sales.po.GroupOrderPrice;
import com.yihg.sales.po.GroupOrderTransport;
import com.yihg.sales.po.GroupRequirement;
import com.yihg.sales.po.GroupRoute;

public class FitOrderVO implements Serializable {

	private boolean isAgency=false;
	private String productCode="";
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

	public boolean isAgency() {
		return isAgency;
	}

	public void setAgency(boolean isAgency) {
		this.isAgency = isAgency;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


}

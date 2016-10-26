package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class TeamGroupVO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8301925455206370276L;

	private TourGroup tourGroup;

	private GroupOrder groupOrder;

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

	// 酒店
	private List<GroupRequirement> hotelGroupRequirementList;
	// 车队
	private List<GroupRequirement> fleetGroupRequirementList;

	// 导游
	private List<GroupRequirement> guideGroupRequirementList;
	// 餐厅
	private List<GroupRequirement> restaurantGroupRequirementList;
	
	//是否是组团社
	private boolean isAgency = false;
	
	private String productCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public boolean isAgency() {
		return isAgency;
	}

	public void setAgency(boolean isAgency) {
		this.isAgency = isAgency;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
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

	public List<GroupRequirement> getHotelGroupRequirementList() {
		return hotelGroupRequirementList;
	}

	public void setHotelGroupRequirementList(
			List<GroupRequirement> hotelGroupRequirementList) {
		this.hotelGroupRequirementList = hotelGroupRequirementList;
	}

	public List<GroupRequirement> getFleetGroupRequirementList() {
		return fleetGroupRequirementList;
	}

	public void setFleetGroupRequirementList(
			List<GroupRequirement> fleetGroupRequirementList) {
		this.fleetGroupRequirementList = fleetGroupRequirementList;
	}

	public List<GroupRequirement> getGuideGroupRequirementList() {
		return guideGroupRequirementList;
	}

	public void setGuideGroupRequirementList(
			List<GroupRequirement> guideGroupRequirementList) {
		this.guideGroupRequirementList = guideGroupRequirementList;
	}

	public List<GroupRequirement> getRestaurantGroupRequirementList() {
		return restaurantGroupRequirementList;
	}

	public void setRestaurantGroupRequirementList(
			List<GroupRequirement> restaurantGroupRequirementList) {
		this.restaurantGroupRequirementList = restaurantGroupRequirementList;
	}

}

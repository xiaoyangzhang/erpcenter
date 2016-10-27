package com.yimayhd.erpcenter.facade.sales.result.grouprequirement;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToRequirementListResult extends BaseStateResult {
	private static final long serialVersionUID = -526315668491787546L;
	private List<GroupRequirement> hotelList;
	private List<GroupRequirement> airList;
	private List<GroupRequirement> trainList;
	private List<GroupRequirement> fleetList;
	private List<GroupRequirement> guideList;
	private List<GroupRequirement> restaurantList;
	private TourGroup tourGroup;
	private List<DicInfo> cdcxList;

	public List<GroupRequirement> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<GroupRequirement> hotelList) {
		this.hotelList = hotelList;
	}

	public List<GroupRequirement> getAirList() {
		return airList;
	}

	public void setAirList(List<GroupRequirement> airList) {
		this.airList = airList;
	}

	public List<GroupRequirement> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<GroupRequirement> trainList) {
		this.trainList = trainList;
	}

	public List<GroupRequirement> getFleetList() {
		return fleetList;
	}

	public void setFleetList(List<GroupRequirement> fleetList) {
		this.fleetList = fleetList;
	}

	public List<GroupRequirement> getGuideList() {
		return guideList;
	}

	public void setGuideList(List<GroupRequirement> guideList) {
		this.guideList = guideList;
	}

	public List<GroupRequirement> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<GroupRequirement> restaurantList) {
		this.restaurantList = restaurantList;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

	public List<DicInfo> getCdcxList() {
		return cdcxList;
	}

	public void setCdcxList(List<DicInfo> cdcxList) {
		this.cdcxList = cdcxList;
	}
}

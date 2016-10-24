package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.operation.po.BookingDelivery;
import com.yihg.operation.po.BookingSupplier;
import com.yihg.sales.po.GroupOrder;
import com.yihg.sales.po.TourGroup;

public class SalesVO implements Serializable {
	private TourGroup  tourGroup;
	private List<GroupOrder> groupOrderList;
	private List<BookingDelivery> deliveryList;
	
	private List<BookingSupplier> hotelList;
	
	private List<BookingSupplier> restaurantList;
	private List<BookingSupplier> fleetList;
	private List<BookingSupplier> scenicsportList;
	private List<BookingSupplier> insuranceList;
	private List<BookingSupplier> airticketList;
	private List<BookingSupplier> trainList;
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<GroupOrder> getGroupOrderList() {
		return groupOrderList;
	}
	public void setGroupOrderList(List<GroupOrder> groupOrderList) {
		this.groupOrderList = groupOrderList;
	}
	public List<BookingDelivery> getDeliveryList() {
		return deliveryList;
	}
	public void setDeliveryList(List<BookingDelivery> deliveryList) {
		this.deliveryList = deliveryList;
	}
	public List<BookingSupplier> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<BookingSupplier> hotelList) {
		this.hotelList = hotelList;
	}
	public List<BookingSupplier> getRestaurantList() {
		return restaurantList;
	}
	public void setRestaurantList(List<BookingSupplier> restaurantList) {
		this.restaurantList = restaurantList;
	}
	public List<BookingSupplier> getFleetList() {
		return fleetList;
	}
	public void setFleetList(List<BookingSupplier> fleetList) {
		this.fleetList = fleetList;
	}
	public List<BookingSupplier> getScenicsportList() {
		return scenicsportList;
	}
	public void setScenicsportList(List<BookingSupplier> scenicsportList) {
		this.scenicsportList = scenicsportList;
	}
	public List<BookingSupplier> getInsuranceList() {
		return insuranceList;
	}
	public void setInsuranceList(List<BookingSupplier> insuranceList) {
		this.insuranceList = insuranceList;
	}
	public List<BookingSupplier> getAirticketList() {
		return airticketList;
	}
	public void setAirticketList(List<BookingSupplier> airticketList) {
		this.airticketList = airticketList;
	}
	public List<BookingSupplier> getTrainList() {
		return trainList;
	}
	public void setTrainList(List<BookingSupplier> trainList) {
		this.trainList = trainList;
	}
	
}

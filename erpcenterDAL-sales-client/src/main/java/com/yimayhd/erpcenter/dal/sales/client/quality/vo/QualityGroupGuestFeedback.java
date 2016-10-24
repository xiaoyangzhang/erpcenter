package com.yimayhd.erpcenter.dal.sales.client.quality.vo;

import java.io.Serializable;

public class QualityGroupGuestFeedback implements Serializable {
	//团信息
	private Integer groupId;
	private String supplierName;
	private String receiverMode;
	private String guestName;
	private String mobile;	
	private String orderNo;
	//评议信息
	private Double guideAttitude;
    private Double guideProfession;
    private Double foodQuality;
    private Double foodEnvironment;
    private Double hotelQuality;
    private Double hotelEnvironment;
    private Double driverAttitude;
    private Double driverEnvironment;
    private Double scenicWhole;
    private Double score;
	private String suggest;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getReceiverMode() {
		return receiverMode;
	}
	public void setReceiverMode(String receiverMode) {
		this.receiverMode = receiverMode;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public Double getGuideAttitude() {
		return guideAttitude;
	}
	public void setGuideAttitude(Double guideAttitude) {
		this.guideAttitude = guideAttitude;
	}
	public Double getGuideProfession() {
		return guideProfession;
	}
	public void setGuideProfession(Double guideProfession) {
		this.guideProfession = guideProfession;
	}
	public Double getFoodQuality() {
		return foodQuality;
	}
	public void setFoodQuality(Double foodQuality) {
		this.foodQuality = foodQuality;
	}
	public Double getFoodEnvironment() {
		return foodEnvironment;
	}
	public void setFoodEnvironment(Double foodEnvironment) {
		this.foodEnvironment = foodEnvironment;
	}
	public Double getHotelQuality() {
		return hotelQuality;
	}
	public void setHotelQuality(Double hotelQuality) {
		this.hotelQuality = hotelQuality;
	}
	public Double getHotelEnvironment() {
		return hotelEnvironment;
	}
	public void setHotelEnvironment(Double hotelEnvironment) {
		this.hotelEnvironment = hotelEnvironment;
	}
	public Double getDriverAttitude() {
		return driverAttitude;
	}
	public void setDriverAttitude(Double driverAttitude) {
		this.driverAttitude = driverAttitude;
	}
	public Double getDriverEnvironment() {
		return driverEnvironment;
	}
	public void setDriverEnvironment(Double driverEnvironment) {
		this.driverEnvironment = driverEnvironment;
	}
	public Double getScenicWhole() {
		return scenicWhole;
	}
	public void setScenicWhole(Double scenicWhole) {
		this.scenicWhole = scenicWhole;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	} 
	
}

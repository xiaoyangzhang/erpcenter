package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;

public class GroupFeedbackGroupStaticsVO implements Serializable {

    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 4837464818012692502L;

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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
    
    
}

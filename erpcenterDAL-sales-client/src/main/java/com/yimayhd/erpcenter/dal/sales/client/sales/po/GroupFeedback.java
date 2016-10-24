package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.Date;

public class GroupFeedback  implements Serializable{
    private Integer id;

    private Integer groupId;

    private Double guideAttitude;

    private Double guideProfession;

    private Double foodQuality;

    private Double foodEnvironment;

    private Double hotelQuality;

    private Double hotelEnvironment;

    private Double driverAttitude;

    private Double driverEnvironment;

    private Double scenicWhole;

    private String suggestion;

    private Integer creatorId;

    private String creatorName;

    private String creatorPic;

    private String creatorIdentityNo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public String getCreatorPic() {
        return creatorPic;
    }

    public void setCreatorPic(String creatorPic) {
        this.creatorPic = creatorPic == null ? null : creatorPic.trim();
    }

    public String getCreatorIdentityNo() {
        return creatorIdentityNo;
    }

    public void setCreatorIdentityNo(String creatorIdentityNo) {
        this.creatorIdentityNo = creatorIdentityNo == null ? null : creatorIdentityNo.trim();
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
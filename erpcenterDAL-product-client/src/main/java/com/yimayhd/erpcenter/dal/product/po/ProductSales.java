package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductSales implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer brandId;
	private String bprice;
	private String eprice;
	private String name;
	private String groupDate;
	/**
	 * 已弃用
	 */
	private String groupDates;
	private String path;
	private String code;
	private String brandName;
	private String travelDays;
	/**
	 * 已弃用
	 */
	private String destProvinceName;
	/**
	 * 已弃用
	 */
	private String destCityName;
	private String nameCity;
	private String nameDuration;
	private String nameTravelMode;
	private String nameStarLevel;
	private String nameMode;
	private String guestNote;
	private String operatorName;	
	
	public String getGroupDates() {
		return groupDates;
	}
	public void setGroupDates(String groupDates) {
		this.groupDates = groupDates;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getTravelDays() {
		return travelDays;
	}
	public void setTravelDays(String travelDays) {
		this.travelDays = travelDays;
	}
	public String getNameCity() {
		return nameCity;
	}
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	public String getNameDuration() {
		return nameDuration;
	}
	public void setNameDuration(String nameDuration) {
		this.nameDuration = nameDuration;
	}
	public String getNameTravelMode() {
		return nameTravelMode;
	}
	public void setNameTravelMode(String nameTravelMode) {
		this.nameTravelMode = nameTravelMode;
	}
	public String getNameStarLevel() {
		return nameStarLevel;
	}
	public void setNameStarLevel(String nameStarLevel) {
		this.nameStarLevel = nameStarLevel;
	}
	public String getNameMode() {
		return nameMode;
	}
	public void setNameMode(String nameMode) {
		this.nameMode = nameMode;
	}
	public String getGuestNote() {
		return guestNote;
	}
	public void setGuestNote(String guestNote) {
		/*if(guestNote.length() > 30){
			guestNote = guestNote.substring(0, 30) + "...";
		}*/
		this.guestNote = guestNote;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBprice() {
		return bprice;
	}
	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
	public String getEprice() {
		return eprice;
	}
	public void setEprice(String eprice) {
		this.eprice = eprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupDate() {
		return groupDate;
	}
	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDestProvinceName() {
		return destProvinceName;
	}
	public void setDestProvinceName(String destProvinceName) {
		this.destProvinceName = destProvinceName;
	}
	public String getDestCityName() {
		return destCityName;
	}
	public void setDestCityName(String destCityName) {
		this.destCityName = destCityName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}	
}
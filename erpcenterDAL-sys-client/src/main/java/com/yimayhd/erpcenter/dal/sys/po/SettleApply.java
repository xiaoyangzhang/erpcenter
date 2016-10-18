package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;

public class SettleApply implements Serializable {
	private Integer id;
	
	private Integer bizId;

	private String serviceNo;

	private String corpFullName;

	private Integer provinceId;

	private String provinceName;

	private Integer cityId;

	private String cityName;

	private Integer areaId;

	private String areaName;

	private Integer townId;

	private String townName;

	private String addrDetail;

	private String referee;

	private String contactMan;

	private String contactPhone;

	private String note;

	private Long createTime;

	private SettleApplyResult settleApplyResult;
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public SettleApplyResult getSettleApplyResult() {
		return settleApplyResult;
	}

	public void setSettleApplyResult(SettleApplyResult settleApplyResult) {
		this.settleApplyResult = settleApplyResult;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo == null ? null : serviceNo.trim();
	}

	public String getCorpFullName() {
		return corpFullName;
	}

	public void setCorpFullName(String corpFullName) {
		this.corpFullName = corpFullName == null ? null : corpFullName.trim();
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName == null ? null : provinceName.trim();
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName == null ? null : townName.trim();
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail == null ? null : addrDetail.trim();
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee == null ? null : referee.trim();
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan == null ? null : contactMan.trim();
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
}
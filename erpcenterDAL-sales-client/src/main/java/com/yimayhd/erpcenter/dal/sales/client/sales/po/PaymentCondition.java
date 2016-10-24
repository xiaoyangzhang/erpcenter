package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class PaymentCondition implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7463374244773807458L;
	
	private Integer supplierId ;
	
	private String startTime ;
	
	private String endTime ;
	
	private String supplierName ;
	
	private String groupCode ;
	
	private String paymentState ;
	
	private String productName ;
	
	private String operatorIds ;
	
	private String operatorName ;
	private String userIds;
	private String dateType ;
	private String page ; 
	private String pageSize ;
	private String level;
	private String provinceId;
	private String cityId;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userName;
	private Integer groupMode ;
	private String type1Name;
	private Integer supplierType;
	private Integer selectDate;
	private String orgIds;
	private String saleOperatorIds;
	private String orgNames;
	private String saleOperatorName;
	private String productBrandName;
	private String cashType;
	private Integer operType;
	private Integer type;//计调类型
	private String userNames;
	
	//车辆订单明细查询条件
	private String carNumMin;
	private String carNumMax;
	private String driverName;
	private String carLisence;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getSaleOperatorName() {
		return saleOperatorName;
	}
	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public Integer getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(Integer selectDate) {
		this.selectDate = selectDate;
	}
	public Integer getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	public String getType1Name() {
		return type1Name;
	}
	public void setType1Name(String type1Name) {
		this.type1Name = type1Name;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCarNumMin() {
		return carNumMin;
	}
	public void setCarNumMin(String carNumMin) {
		this.carNumMin = carNumMin;
	}
	public String getCarNumMax() {
		return carNumMax;
	}
	public void setCarNumMax(String carNumMax) {
		this.carNumMax = carNumMax;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCarLisence() {
		return carLisence;
	}
	public void setCarLisence(String carLisence) {
		this.carLisence = carLisence;
	}
	
	
}

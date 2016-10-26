package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingSupplierPO implements Serializable{

	private Integer id;//bookingSupplier表主键
	private Integer supplierId;
	private Integer supplierType;
	private Integer groupId;
	public Integer getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	private String supplierName;
	private Date itemDate;
	private Date itemDateTo;
	private String type1Name;
	private Integer type1Id;
	public Integer getType1Id() {
		return type1Id;
	}
	public void setType1Id(Integer type1Id) {
		this.type1Id = type1Id;
	}
	private Double itemPrice;
	private Double itemNum;
	private Double itemTotal;
	private String cashType;
	private String type2Name;
	private String driverName;
	private String driverTel;
	private Integer driverId;
	private BigDecimal totalCash;
	private String carLisence;
	private String contact;
	private String contactMobile;
	private Integer userId;
	private String userName;
	private String guideInfo;
	
public String getGuideInfo() {
		return guideInfo;
	}
	public void setGuideInfo(String guideInfo) {
		this.guideInfo = guideInfo;
	}
public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
public Date getItemDateTo() {
		return itemDateTo;
	}
	public void setItemDateTo(Date itemDateTo) {
		this.itemDateTo = itemDateTo;
	}
public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
public String getCarLisence() {
		return carLisence;
	}
	public void setCarLisence(String carLisence) {
		this.carLisence = carLisence;
	}
public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
		  public Boolean getCanDelete() {    	
			return (this.stateFinance==null || this.stateFinance==0) && (this.totalCash == null || this.totalCash.compareTo(new BigDecimal(0))==0);
		}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	private Integer stateBooking;
	private Integer stateFinance;
	public Integer getStateBooking() {
		return stateBooking;
	}
	public void setStateBooking(Integer stateBooking) {
		this.stateBooking = stateBooking;
	}
	public Integer getStateFinance() {
		return stateFinance;
	}
	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Date getItemDate() {
		return itemDate;
	}
	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}
	public String getType1Name() {
		return type1Name;
	}
	public void setType1Name(String type1Name) {
		this.type1Name = type1Name;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Double getItemNum() {
		return itemNum;
	}
	public void setItemNum(Double itemNum) {
		this.itemNum = itemNum;
	}
	public Double getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public String getType2Name() {
		return type2Name;
	}
	public void setType2Name(String type2Name) {
		this.type2Name = type2Name;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverTel() {
		return driverTel;
	}
	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}
	
	
}

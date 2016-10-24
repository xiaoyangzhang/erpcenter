package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;

public class PaymentExportVO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7863934313416263800L;
	private String startTime ;
	private String endTime ;
	private String supplierName ;
	private Integer provinceId ;
	private String cityId ;
	private String groupCode ;
	
	private Integer paymentState ;
	private String productName ;
	private String operatorIds ;
	private String saleOperatorIds ;
	private String groupMode ;
	private Integer type ;
	private String orgIds;
	
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	private Integer supplierId ;
	
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(Integer paymentState) {
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
	public String getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(String groupMode) {
		this.groupMode = groupMode;
	}
	
	
	
}

package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;
import java.util.Date;

public class ProductGuestCondition implements Serializable {
	private Integer dateType;
	private Date startDate;
	private Date endDate;
	private String supplierName;
	private String productName;
	private Integer orderMode;
	private Integer operatorType;
	private String operatorIds;
	private Long startDateNum;
	private Long endDateNum;
	private Integer bizId;
	private Integer showNum;
	private String orgNames;

	private String orgIds;
	
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	
	public Integer getShowNum() {
		return showNum;
	}
	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public Integer getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(Integer orderMode) {
		this.orderMode = orderMode;
	}
	public Long getStartDateNum() {
		return startDateNum;
	}
	public void setStartDateNum(Long startDateNum) {
		this.startDateNum = startDateNum;
	}
	public Long getEndDateNum() {
		return endDateNum;
	}
	public void setEndDateNum(Long endDateNum) {
		this.endDateNum = endDateNum;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
	
}

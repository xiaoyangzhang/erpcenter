package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;
import java.util.Date;

public class DeparentmentOrderCondition implements Serializable {
	private Integer bizId;
	private Date startTime;
	private Date endTime;
	private Long startTimeNum;
	private Long endTimeNum;
	private int dateType;
	private String productName;
	private String supplierName;
	private String orgIds;
	private String operatorIds;
	
	private String saleOperatorIds;
	
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getStartTimeNum() {
		return startTimeNum;
	}
	public void setStartTimeNum(Long startTimeNum) {
		this.startTimeNum = startTimeNum;
	}
	public Long getEndTimeNum() {
		return endTimeNum;
	}
	public void setEndTimeNum(Long endTimeNum) {
		this.endTimeNum = endTimeNum;
	}
	public int getDateType() {
		return dateType;
	}
	public void setDateType(int dateType) {
		this.dateType = dateType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
	
}

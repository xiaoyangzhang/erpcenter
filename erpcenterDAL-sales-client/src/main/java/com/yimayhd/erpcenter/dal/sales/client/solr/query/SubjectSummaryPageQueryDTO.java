package com.yimayhd.erpcenter.dal.sales.client.solr.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class SubjectSummaryPageQueryDTO extends PageQuery implements Serializable {
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private int bizId;
	private long startTime;
	private long endTime;
	private String cashType;
	private String cashType2;
	private String cashType3;
	private int supplierType;
	private String operatorIds;
	private String supplierName;
	
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public String getCashType2() {
		return cashType2;
	}
	public void setCashType2(String cashType2) {
		this.cashType2 = cashType2;
	}
	public String getCashType3() {
		return cashType3;
	}
	public void setCashType3(String cashType3) {
		this.cashType3 = cashType3;
	}
	public int getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(int supplierType) {
		this.supplierType = supplierType;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}

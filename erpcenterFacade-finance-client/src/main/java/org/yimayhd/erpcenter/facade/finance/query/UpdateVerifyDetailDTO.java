package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class UpdateVerifyDetailDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer bizId;
	private Integer supplierType;
	private Integer verifyId;
	private String records;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	public Integer getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	
}

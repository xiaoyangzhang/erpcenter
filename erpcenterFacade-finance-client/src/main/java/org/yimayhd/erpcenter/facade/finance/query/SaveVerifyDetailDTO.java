package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class SaveVerifyDetailDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer bizId;
	private Integer supplierType; 
	private Integer verifyId; 
	private String ids;
	
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}

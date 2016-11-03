package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class ToImpAiYouOrderDTO implements Serializable {

	private static final long serialVersionUID = -8733452221312754061L;

	private Integer productId;
	private String code;
	private String date;
	private String supplierCode;
	private String aiyouId; 
	private PlatformEmployeePo curUser;
	private int bizId;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getAiyouId() {
		return aiyouId;
	}
	public void setAiyouId(String aiyouId) {
		this.aiyouId = aiyouId;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	
}

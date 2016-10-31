package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

public class EditSupplierAndReceiveModeDTO implements Serializable {
	private static final long serialVersionUID = -2581843911273746290L;
	private String supplierCode;
	private String receiveMode;
	private Integer orderId;
	
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}

package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class GroupGuidePrintPo implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3387816662688397829L;
	private String supplierType ;
	private String supplierName ;
	private String contacktWay ;
	private String paymentWay ;
	private String detail ;
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getContacktWay() {
		return contacktWay;
	}
	public void setContacktWay(String contacktWay) {
		this.contacktWay = contacktWay;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}

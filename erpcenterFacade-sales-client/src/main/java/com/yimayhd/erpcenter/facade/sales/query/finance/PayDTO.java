package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;

public class PayDTO implements Serializable{
	
	private static final long serialVersionUID = -2196121659966197045L;
	
	private FinancePay financePay;
	private Integer bizId;
	private String employeeId;
	private String employeeName;
	
	public FinancePay getFinancePay() {
		return financePay;
	}
	public void setFinancePay(FinancePay financePay) {
		this.financePay = financePay;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}

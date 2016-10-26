package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.io.Serializable;

public class AuditListDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String data;
	private String financeGuides;
	private String employeeId;
	private String employeeName;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getFinanceGuides() {
		return financeGuides;
	}
	public void setFinanceGuides(String financeGuides) {
		this.financeGuides = financeGuides;
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

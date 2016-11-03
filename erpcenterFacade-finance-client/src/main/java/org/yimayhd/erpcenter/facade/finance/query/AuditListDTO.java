package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class AuditListDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String data;
	private String financeGuides;
	private Integer employeeId;
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
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}

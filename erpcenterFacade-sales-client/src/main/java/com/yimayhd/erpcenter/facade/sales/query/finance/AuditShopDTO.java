package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.io.Serializable;

public class AuditShopDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;

	private String checkedIds;
	private String unCheckedIds;
	private String employeeId;
	private String employeeName;
	
	public String getCheckedIds() {
		return checkedIds;
	}
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}
	public String getUnCheckedIds() {
		return unCheckedIds;
	}
	public void setUnCheckedIds(String unCheckedIds) {
		this.unCheckedIds = unCheckedIds;
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

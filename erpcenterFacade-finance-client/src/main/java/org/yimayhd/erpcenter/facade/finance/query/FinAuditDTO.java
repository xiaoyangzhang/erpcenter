package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class FinAuditDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer groupId;
	private String employeeId;
	private String employeeName;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

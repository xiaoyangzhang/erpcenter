package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class UnsealDTO implements Serializable{
	
	private static final long serialVersionUID = -2196121659966197045L;
	
	private String groupIds;
	private String employeeId;
	private String employeeName;
	
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
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

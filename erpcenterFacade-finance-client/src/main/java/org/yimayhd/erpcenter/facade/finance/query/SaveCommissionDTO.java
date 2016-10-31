package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class SaveCommissionDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer groupId;
	private String content;
	private Integer employeeId;
	private String employeeName;
	private Integer bizId;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

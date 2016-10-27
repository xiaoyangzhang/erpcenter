package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class AuditShopDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;

	private String checkedIds;
	private String unCheckedIds;
	private Integer employeeId;
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

package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class AuditDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer groupId;
	private String feeType;
	private String checkedIds;
	private String unCheckedIds;
	private String comCheckedIds;
	private String comUnCheckedIds; 
	private String financeGuides;
	private String priceCheckedIds;
	private String priceUnCheckedIds;
	private String employeeId;
	private String employeeName;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
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
	public String getComCheckedIds() {
		return comCheckedIds;
	}
	public void setComCheckedIds(String comCheckedIds) {
		this.comCheckedIds = comCheckedIds;
	}
	public String getComUnCheckedIds() {
		return comUnCheckedIds;
	}
	public void setComUnCheckedIds(String comUnCheckedIds) {
		this.comUnCheckedIds = comUnCheckedIds;
	}
	public String getFinanceGuides() {
		return financeGuides;
	}
	public void setFinanceGuides(String financeGuides) {
		this.financeGuides = financeGuides;
	}
	public String getPriceCheckedIds() {
		return priceCheckedIds;
	}
	public void setPriceCheckedIds(String priceCheckedIds) {
		this.priceCheckedIds = priceCheckedIds;
	}
	public String getPriceUnCheckedIds() {
		return priceUnCheckedIds;
	}
	public void setPriceUnCheckedIds(String priceUnCheckedIds) {
		this.priceUnCheckedIds = priceUnCheckedIds;
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

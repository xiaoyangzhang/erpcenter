package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class InRecordDTO extends BaseListPage{
	
	private static final long serialVersionUID = -2196121659966197045L;
	
	private Integer bizId;
	private String depDateFrom;
	private String depDateTo;
	private String groupCode;
	private String billType;
	private String guideName;
	private String productName;
	private String billState;
	private String billSupplier;
	private Set<Integer> set;
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getDepDateFrom() {
		return depDateFrom;
	}
	public void setDepDateFrom(String depDateFrom) {
		this.depDateFrom = depDateFrom;
	}
	public String getDepDateTo() {
		return depDateTo;
	}
	public void setDepDateTo(String depDateTo) {
		this.depDateTo = depDateTo;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public String getBillSupplier() {
		return billSupplier;
	}
	public void setBillSupplier(String billSupplier) {
		this.billSupplier = billSupplier;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	
	
}

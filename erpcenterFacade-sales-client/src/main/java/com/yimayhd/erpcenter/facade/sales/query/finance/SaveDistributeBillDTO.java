package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.io.Serializable;

public class SaveDistributeBillDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String billList;
	private String groupId;
	private String guideId;
	private String type;
	private String loginName;
	public String getBillList() {
		return billList;
	}
	public void setBillList(String billList) {
		this.billList = billList;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGuideId() {
		return guideId;
	}
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
}

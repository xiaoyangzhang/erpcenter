package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class SaveDistributeBillDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String billList;
	private Integer groupId;
	private Integer guideId;
	private String type;
	private String loginName;
	public String getBillList() {
		return billList;
	}
	public void setBillList(String billList) {
		this.billList = billList;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
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

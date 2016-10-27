package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

public class CheckBillDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String id;
	private String guideId;
	private String groupCode;
	private String appliState;
	private Integer bizId;
	
	public String getAppliState() {
		return appliState;
	}
	public void setAppliState(String appliState) {
		this.appliState = appliState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGuideId() {
		return guideId;
	}
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
}

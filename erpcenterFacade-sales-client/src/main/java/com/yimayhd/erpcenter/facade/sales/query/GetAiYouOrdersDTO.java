package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

public class GetAiYouOrdersDTO implements Serializable {

	private static final long serialVersionUID = -8733452221312754061L;

	private String code;
	private String port;
	private String startDate;
	private String endDate;
	private String groupNum;
	private Integer bizId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	
}

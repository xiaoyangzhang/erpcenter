package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;

public class SaveDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private Integer id;
	private Integer resourceId;
	private String resourceNumber;
	private Integer groupOrderId;
	private String jsonOrders;
	private Integer curUserId;
	private String curUserName;
	
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getGroupOrderId() {
		return groupOrderId;
	}
	public void setGroupOrderId(Integer groupOrderId) {
		this.groupOrderId = groupOrderId;
	}
	public String getResourceNumber() {
		return resourceNumber;
	}
	public void setResourceNumber(String resourceNumber) {
		this.resourceNumber = resourceNumber;
	}
	public String getJsonOrders() {
		return jsonOrders;
	}
	public void setJsonOrders(String jsonOrders) {
		this.jsonOrders = jsonOrders;
	}
	public Integer getCurUserId() {
		return curUserId;
	}
	public void setCurUserId(Integer curUserId) {
		this.curUserId = curUserId;
	}
	public String getCurUserName() {
		return curUserName;
	}
	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}

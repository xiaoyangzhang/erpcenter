package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;

public class SavePickUpDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private Integer orderId;
	private Integer resourceId;
	private String jsonString;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}

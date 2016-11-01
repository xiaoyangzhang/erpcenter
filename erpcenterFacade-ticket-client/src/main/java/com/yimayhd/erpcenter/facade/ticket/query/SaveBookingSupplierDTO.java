package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;

public class SaveBookingSupplierDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer requestId;
	private Integer bizId;
	private Integer operatorId;
	private String operatorName;
	
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
}

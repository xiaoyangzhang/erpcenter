package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;

public class RejectDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer requestId;
	private Integer bizId;
	private Integer opId;
	private String opName;
	private String comment;
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
	public Integer getOpId() {
		return opId;
	}
	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}

package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;

public class GetResourceBoDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private Integer id;
	
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

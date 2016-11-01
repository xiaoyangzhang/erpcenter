package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;
import java.util.Map;

public class ShowResourceDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Map<String, String> param;
	private Integer bizId;
	
	public Map<String, String> getParam() {
		return param;
	}
	public void setParam(Map<String, String> param) {
		this.param = param;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
}

package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class QueryVerifyDetailOrdersDTO implements Serializable{
	
	private static final long serialVersionUID = -2196121659966197045L;
	
	private Integer bizId;
	private Map<String, Object> paramters; 
	private Set<Integer> set;
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Map<String, Object> getParamters() {
		return paramters;
	}
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	
	
}

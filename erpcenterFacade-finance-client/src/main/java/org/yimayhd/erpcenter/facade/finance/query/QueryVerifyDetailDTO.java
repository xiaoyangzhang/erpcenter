package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;
import java.util.Set;

public class QueryVerifyDetailDTO implements Serializable{
	
	private static final long serialVersionUID = -2196121659966197045L;
	
	private Integer bizId;
	private String verifyId;
	private Set<Integer> set;
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	
	
}

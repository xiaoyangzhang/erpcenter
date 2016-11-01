package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class QueryVerifyDetailTempDTO extends BaseListPage{
	
	private static final long serialVersionUID = -2196121659966197045L;
	
	private Integer bizId;
	private Map paramters;
	private Set<Integer> set;
	
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Map getParamters() {
		return paramters;
	}
	public void setParamters(Map paramters) {
		this.paramters = paramters;
	}
	
}

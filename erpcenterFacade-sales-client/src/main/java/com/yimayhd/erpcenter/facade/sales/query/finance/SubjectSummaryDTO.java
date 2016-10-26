package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class SubjectSummaryDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String saleOperatorIds;
	private String orgIds;
	private Integer bizId;
	private Map<String, Object> paramters;
	private Set<Integer> set;
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
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

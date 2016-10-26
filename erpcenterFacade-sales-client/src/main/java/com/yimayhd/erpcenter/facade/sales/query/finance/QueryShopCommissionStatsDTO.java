package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.facade.sales.BaseListPage;

public class QueryShopCommissionStatsDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String saleOperatorIds;
	private String orgIds;
	private Map<String,Object> paramters;
	private Set<Integer> set;
	private Integer bizId;
	
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
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
    
	
}

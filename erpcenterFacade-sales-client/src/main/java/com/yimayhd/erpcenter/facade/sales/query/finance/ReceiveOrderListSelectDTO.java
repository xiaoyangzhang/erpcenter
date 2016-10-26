package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.facade.sales.BaseListPage;

public class ReceiveOrderListSelectDTO extends BaseListPage{

	private static final long serialVersionUID = -1884288355774741306L;
	
	private Map<String,Object> paramters;
	private Integer bizId;
	private Set<Integer> set;
	private String saleOperatorIds;
	private String orgIds;
	
	public Map<String, Object> getParamters() {
		return paramters;
	}
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
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
}

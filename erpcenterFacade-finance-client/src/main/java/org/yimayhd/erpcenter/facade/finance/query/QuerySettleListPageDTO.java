package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class QuerySettleListPageDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String saleOperatorIds;
	private String orgIds;
	private Integer bizId;
	private Set<Integer> set;
	private Map<String,Object> paramters;
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
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public Map<String, Object> getParamters() {
		return paramters;
	}
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}
	
	
}

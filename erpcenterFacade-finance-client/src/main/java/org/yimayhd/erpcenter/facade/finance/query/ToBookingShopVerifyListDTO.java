package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class ToBookingShopVerifyListDTO extends BaseListPage{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private String guideName;
	private String saleOperatorIds;
	private String orgIds;
	private Integer bizId;
	private Set<Integer> set;
	private Map<String,Object> paramters;
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
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

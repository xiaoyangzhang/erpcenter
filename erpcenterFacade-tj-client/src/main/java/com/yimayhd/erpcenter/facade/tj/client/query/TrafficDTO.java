package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class TrafficDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bizId;
	private String orgIds;
	private String saleOperatorIds;
	private Integer pageSize;
	private Integer page;
	private TourGroupVO group;
	private Map<String,Object> pm;
	private Set<Integer> dataUserIdSet;
	private String dataUserIdString;
	
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public TourGroupVO getGroup() {
		return group;
	}
	public void setGroup(TourGroupVO group) {
		this.group = group;
	}
	public Map<String, Object> getPm() {
		return pm;
	}
	public void setPm(Map<String, Object> pm) {
		this.pm = pm;
	}
	public Set<Integer> getDataUserIdSet() {
		return dataUserIdSet;
	}
	public void setDataUserIdSet(Set<Integer> dataUserIdSet) {
		this.dataUserIdSet = dataUserIdSet;
	}
	public String getDataUserIdString() {
		return dataUserIdString;
	}
	public void setDataUserIdString(String dataUserIdString) {
		this.dataUserIdString = dataUserIdString;
	}

}

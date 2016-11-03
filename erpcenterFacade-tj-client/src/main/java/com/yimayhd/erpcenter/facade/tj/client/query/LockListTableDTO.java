package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class LockListTableDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pageSize;
	private Integer page;
	private int bizId;
	private Set<Integer> dataUserIdSet;
	private Map<String,Object> pmBean;
	
	public Map<String, Object> getPmBean() {
		return pmBean;
	}
	public void setPmBean(Map<String, Object> pmBean) {
		this.pmBean = pmBean;
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
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public Set<Integer> getDataUserIdSet() {
		return dataUserIdSet;
	}
	public void setDataUserIdSet(Set<Integer> dataUserIdSet) {
		this.dataUserIdSet = dataUserIdSet;
	}
	
}

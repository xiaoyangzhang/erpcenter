package com.yimayhd.erpcenter.facade.sales.query.quality;

import java.io.Serializable;
import java.util.Map;

public class SupplierCommentListDTO implements Serializable {
	private static final long serialVersionUID = -6858145404460154170L;
	private Integer pageSize;
	private Integer page;
	private Integer groupId;
	private Integer supplierId;
	private String theKey;
	
	private Map queryParamters;

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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getTheKey() {
		return theKey;
	}

	public void setTheKey(String theKey) {
		this.theKey = theKey;
	}

	public Map getQueryParamters() {
		return queryParamters;
	}

	public void setQueryParamters(Map queryParamters) {
		this.queryParamters = queryParamters;
	}
}

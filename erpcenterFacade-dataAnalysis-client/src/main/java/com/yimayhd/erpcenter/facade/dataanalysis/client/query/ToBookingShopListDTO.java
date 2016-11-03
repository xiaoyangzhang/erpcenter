package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

public class ToBookingShopListDTO extends BaseDTO {
	private static final long serialVersionUID = -4925997676545995907L;
	private Integer pageSize;
	private Integer page;
	private Integer bizId;
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

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Map getQueryParamters() {
		return queryParamters;
	}

	public void setQueryParamters(Map queryParamters) {
		this.queryParamters = queryParamters;
	}
}

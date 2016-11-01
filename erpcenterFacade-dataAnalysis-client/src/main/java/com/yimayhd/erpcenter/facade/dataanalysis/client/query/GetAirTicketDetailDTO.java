package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.HashMap;

public class GetAirTicketDetailDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> parameter;
	private Integer page;
	private Integer pageSize;

	public HashMap<String, String> getParameter() {
		return parameter;
	}

	public void setParameter(HashMap<String, String> parameter) {
		this.parameter = parameter;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}

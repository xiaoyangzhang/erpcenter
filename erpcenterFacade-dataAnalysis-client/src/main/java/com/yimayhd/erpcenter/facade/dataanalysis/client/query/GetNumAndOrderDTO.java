package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

public class GetNumAndOrderDTO extends BaseDTO {
	private static final long serialVersionUID = -4493374055257335873L;

	private Map paramters;
	
	private String sl;
	private String ssl;
	private Integer page;
	private Integer pageSize;
	private String svc;

	public Map getParamters() {
		return paramters;
	}

	public void setParamters(Map paramters) {
		this.paramters = paramters;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getSsl() {
		return ssl;
	}

	public void setSsl(String ssl) {
		this.ssl = ssl;
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

	public String getSvc() {
		return svc;
	}

	public void setSvc(String svc) {
		this.svc = svc;
	}
}

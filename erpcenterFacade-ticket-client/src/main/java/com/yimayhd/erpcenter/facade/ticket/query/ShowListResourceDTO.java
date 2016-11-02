package com.yimayhd.erpcenter.facade.ticket.query;

import com.yimayhd.erpcenter.facade.ticket.BaseListPage;
public class ShowListResourceDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String ajax;
	private String apply;
	private String depDateFrom;
	private String depDateTo;
	private String dateType;
	private String resourceNumber;
	private String endIssueDateFrom;
	private String endIssueDateTo;
	private String depCity;
	private String lineName;
	private String type;
	private Integer bizId;
	
	public String getAjax() {
		return ajax;
	}
	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	public String getDepDateFrom() {
		return depDateFrom;
	}
	public void setDepDateFrom(String depDateFrom) {
		this.depDateFrom = depDateFrom;
	}
	public String getDepDateTo() {
		return depDateTo;
	}
	public void setDepDateTo(String depDateTo) {
		this.depDateTo = depDateTo;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getResourceNumber() {
		return resourceNumber;
	}
	public void setResourceNumber(String resourceNumber) {
		this.resourceNumber = resourceNumber;
	}
	public String getEndIssueDateFrom() {
		return endIssueDateFrom;
	}
	public void setEndIssueDateFrom(String endIssueDateFrom) {
		this.endIssueDateFrom = endIssueDateFrom;
	}
	public String getEndIssueDateTo() {
		return endIssueDateTo;
	}
	public void setEndIssueDateTo(String endIssueDateTo) {
		this.endIssueDateTo = endIssueDateTo;
	}
	public String getDepCity() {
		return depCity;
	}
	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
}

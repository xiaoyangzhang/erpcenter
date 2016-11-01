package com.yimayhd.erpcenter.facade.ticket.query;

import com.yimayhd.erpcenter.facade.ticket.BaseListPage;

public class ShowListDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String dateFrom;
	private String dateTo;
	private String dateType;
	private String productName;
	private String lineName;
	private String contactName;
	private String orderNo;
	private String endIssueDateFrom;
	private String endIssueDateTo;
	private String type;
	private boolean isArrange;
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isArrange() {
		return isArrange;
	}
	public void setArrange(boolean isArrange) {
		this.isArrange = isArrange;
	}
	
}

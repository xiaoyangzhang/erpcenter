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
	private Integer bizId;
	private String receiveMode;
	private String issueStatus;
	private String depCity;
	private String dataUser;
	
	public String getDataUser() {
		return dataUser;
	}
	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getDepCity() {
		return depCity;
	}
	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
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

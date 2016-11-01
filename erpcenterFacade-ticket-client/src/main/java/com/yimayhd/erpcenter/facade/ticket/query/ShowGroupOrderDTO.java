package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.facade.ticket.BaseListPage;

public class ShowGroupOrderDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String departureDateFrom;
	private String departureDateTo;
	private String orderNo;
	private String productName;
	private String receiveMode;
	private String dataUser;
	private String airApplyState;
	private Integer bizId;
	public String getDepartureDateFrom() {
		return departureDateFrom;
	}
	public void setDepartureDateFrom(String departureDateFrom) {
		this.departureDateFrom = departureDateFrom;
	}
	public String getDepartureDateTo() {
		return departureDateTo;
	}
	public void setDepartureDateTo(String departureDateTo) {
		this.departureDateTo = departureDateTo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public String getDataUser() {
		return dataUser;
	}
	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}
	public String getAirApplyState() {
		return airApplyState;
	}
	public void setAirApplyState(String airApplyState) {
		this.airApplyState = airApplyState;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	
	
}

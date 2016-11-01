package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

public class ToEditTaobaoOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bizId;
	private Integer operType;
	private int orderId;
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	

}

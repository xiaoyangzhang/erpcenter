package com.yimayhd.erpcenter.dal.sales.client.message;

import java.io.Serializable;

public class OrderGuestUpdatedMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1907619118090229343L;
	
	/**
	 * 订单ID
	 */
	private Integer orderId;
	
	/**
	 * 游客id
	 */
	private Integer guestId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGuestId() {
		return guestId;
	}

	public void setGuestId(Integer guestId) {
		this.guestId = guestId;
	}
	
	

}

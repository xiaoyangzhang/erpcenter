package com.yimayhd.erpcenter.dal.sales.client.message;

import java.io.Serializable;

public class OrderPriceUpdatedMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7114270834359182041L;

	/**
	 * 订单ID
	 */
	private Integer orderId;

	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}

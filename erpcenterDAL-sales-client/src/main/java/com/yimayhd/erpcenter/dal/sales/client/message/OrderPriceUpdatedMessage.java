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

	/**
	 * 订单价格id
	 */
	private Integer priceId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

}

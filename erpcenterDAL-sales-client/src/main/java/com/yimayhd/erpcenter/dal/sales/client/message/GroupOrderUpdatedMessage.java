package com.yimayhd.erpcenter.dal.sales.client.message;

import java.io.Serializable;

/**
 * 
 * 团信息变更消息对象
 *
 */
public class GroupOrderUpdatedMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8577492138535591139L;
	
	/**
	 * 订单id
	 */
	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}

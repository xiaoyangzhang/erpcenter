package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;

public class ToEditFirOrderDTO implements Serializable {

	private Integer orderId;
	private Integer bizId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
}

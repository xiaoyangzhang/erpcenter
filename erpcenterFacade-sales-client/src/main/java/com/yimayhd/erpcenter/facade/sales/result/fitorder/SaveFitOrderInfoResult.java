package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class SaveFitOrderInfoResult extends BaseStateResult {
	private static final long serialVersionUID = 5041537676536616670L;
	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}

package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;

public class ToFitOrderListDTO implements Serializable {
	private static final long serialVersionUID = 8616737122089556539L;
	private Integer bizId;

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
}

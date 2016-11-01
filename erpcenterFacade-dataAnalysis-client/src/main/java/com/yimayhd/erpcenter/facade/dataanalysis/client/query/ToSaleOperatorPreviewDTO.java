package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;

public class ToSaleOperatorPreviewDTO extends BaseDTO {
	private static final long serialVersionUID = -2118613260080746321L;
	private SaleOperatorVo order;

	public SaleOperatorVo getOrder() {
		return order;
	}

	public void setOrder(SaleOperatorVo order) {
		this.order = order;
	}
}

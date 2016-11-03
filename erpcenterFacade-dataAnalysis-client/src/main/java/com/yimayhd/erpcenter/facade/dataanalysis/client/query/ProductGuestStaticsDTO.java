package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;

public class ProductGuestStaticsDTO extends BaseDTO {
	private static final long serialVersionUID = -4794367813480742268L;
	private ProductGuestCondition condition;

	public ProductGuestCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductGuestCondition condition) {
		this.condition = condition;
	}
}

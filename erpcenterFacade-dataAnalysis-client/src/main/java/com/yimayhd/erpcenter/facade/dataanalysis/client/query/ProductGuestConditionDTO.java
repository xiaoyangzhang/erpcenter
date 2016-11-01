package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;

public class ProductGuestConditionDTO extends BaseDTO{

	/**
	 * @author liyong
	 * 2016年11月1日 描述：
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductGuestCondition productGuestCondition;
	
	public void setProductGuestCondition(ProductGuestCondition productGuestCondition) {
		this.productGuestCondition = productGuestCondition;
	}
	
	public ProductGuestCondition getProductGuestCondition() {
		return productGuestCondition;
	}

}

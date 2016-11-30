package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;

public class ProductSupplierConditionDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ProductSupplierCondition condition = null;
	
	private Integer productId;
	
	
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public ProductSupplierCondition getCondition() {
		return condition;
	}
	public void setCondition(ProductSupplierCondition condition) {
		this.condition = condition;
	}
}

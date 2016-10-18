package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;

public class ProductRemarkDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductRemark productRemark;

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}
	
	

}

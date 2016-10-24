package com.yimayhd.erpcenter.dal.product.message;

import java.io.Serializable;

public class ProductInfoUpdateMessageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410263651809104616L;
	
	/**
	 * 产品id
	 */
	private Integer productId;

	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

}

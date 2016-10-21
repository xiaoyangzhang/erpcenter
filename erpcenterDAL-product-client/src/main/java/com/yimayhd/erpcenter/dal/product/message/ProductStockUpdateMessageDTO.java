package com.yimayhd.erpcenter.dal.product.message;

import java.io.Serializable;

public class ProductStockUpdateMessageDTO implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 715107642236382817L;
	

	/**
	 * 产品id
	 */
	private Integer productId;
	
	/**
	 * 库存id
	 */
	private Integer stockId;

	
	
	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

}

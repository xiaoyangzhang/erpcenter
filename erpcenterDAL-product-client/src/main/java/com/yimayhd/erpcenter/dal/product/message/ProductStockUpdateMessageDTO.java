package com.yimayhd.erpcenter.dal.product.message;

import java.io.Serializable;
import java.util.Date;

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
	 * 库存日期
	 */
	private Date itemDate;

	
	public Date getItemDate() {
		return itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

}

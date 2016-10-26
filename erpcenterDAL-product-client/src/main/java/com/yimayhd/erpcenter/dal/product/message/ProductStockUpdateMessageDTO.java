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
	 * 库存开始日期
	 */
	private Date itemStartDate;
	
	/**
	 * 库存结束日期
	 */
	private Date itemEndDate;

	
	public Date getItemStartDate() {
		return itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}

	public Date getItemEndDate() {
		return itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		this.itemEndDate = itemEndDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

}

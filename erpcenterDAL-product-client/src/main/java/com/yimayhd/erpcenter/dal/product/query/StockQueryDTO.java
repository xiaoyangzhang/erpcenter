package com.yimayhd.erpcenter.dal.product.query;

import java.io.Serializable;
import java.util.Date;

public class StockQueryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3806606053949100971L;
	
	/**
	 * 库存id
	 */
	private Integer stockId;
	
	/**
	 * 产品id
	 */
	private Integer productId ;
	
	/**
	 * 库存开始时间
	 */
	private Date startDate;
	
	/**
	 * 库存接受时间
	 */
	private Date endDate;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}

package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.Date;

public class StockStaticsResultItemVo implements Serializable {
	private Date groupDate;
	private Integer stockCount;
	private Integer receiveCount;
	private Integer leftCount;
	public Date getGroupDate() {
		return groupDate;
	}
	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Integer getReceiveCount() {
		return receiveCount;
	}
	public void setReceiveCount(Integer receiveCount) {
		this.receiveCount = receiveCount;
	}
	public Integer getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}
	
	
}

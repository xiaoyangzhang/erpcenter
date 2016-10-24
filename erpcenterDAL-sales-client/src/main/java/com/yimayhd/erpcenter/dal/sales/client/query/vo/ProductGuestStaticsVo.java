package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;

public class ProductGuestStaticsVo implements Serializable {
	private String productBrandName;
	private String productName;
	private Integer guestCnt;
	private Integer orderCount;
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getGuestCnt() {
		return guestCnt;
	}
	public void setGuestCnt(Integer guestCnt) {
		this.guestCnt = guestCnt;
	}
}

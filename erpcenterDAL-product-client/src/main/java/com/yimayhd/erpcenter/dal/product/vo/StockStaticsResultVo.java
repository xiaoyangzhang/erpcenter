package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.List;

public class StockStaticsResultVo implements Serializable {
	private Integer productId;
	private String brandName;
	private String productName;
	List<StockStaticsResultItemVo> itemVoList;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<StockStaticsResultItemVo> getItemVoList() {
		return itemVoList;
	}
	public void setItemVoList(List<StockStaticsResultItemVo> itemVoList) {
		this.itemVoList = itemVoList;
	}
	
}

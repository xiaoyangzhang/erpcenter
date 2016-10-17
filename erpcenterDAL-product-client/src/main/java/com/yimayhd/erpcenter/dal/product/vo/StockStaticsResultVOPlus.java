package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductStock;


public class StockStaticsResultVOPlus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String brandName;
	private String nameCity;
	private String productName;
	private Date itemDate;
	private Integer  stockCount;
	private Integer receiveCount;
	private String stockInfo;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStockInfo() {
		return stockInfo;
	}
	public void setStockInfo(String stockInfo) {
		this.stockInfo = stockInfo;
	}
	public Date getItemDate() {
		return itemDate;
	}
	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
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
	public String getNameCity() {
		return nameCity;
	}
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	List<ProductStock> stockList;
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
	
	public List<ProductStock> getStockList() {
		return stockList;
	}
	public void setStockList(List<ProductStock> stockList) {
		this.stockList = stockList;
	}
	
	
}

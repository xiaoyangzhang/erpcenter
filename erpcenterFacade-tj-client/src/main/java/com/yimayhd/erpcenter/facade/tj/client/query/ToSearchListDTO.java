package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.po.ProductInfo;

public class ToSearchListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductInfo productInfo;
	private String productName;
	private String name;
	private Integer page;
	private Integer pageSize;
	private int bizId;
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	
	
	
}

package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.Date;

public class StockStaticCondition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer bizId;
	private String productName;
	private Integer brandId;
	private Date groupDate;
	private Date toGroupDate;
	private Integer page;
	private Integer pageSize;
	private Integer orgId;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}	
	public Date getGroupDate() {
		return groupDate;
	}
	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}
	public Integer getPage() {
		if(page==null){
			page = 1;
		}
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
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	public void setToGroupDate(Date toGroupDate) {
		this.toGroupDate = toGroupDate;
	}
	public Date getToGroupDate() {
		return toGroupDate;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}

package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class ProductRight implements Serializable {
	private Integer id;
	private Integer productId;
	private Integer orgId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	
}

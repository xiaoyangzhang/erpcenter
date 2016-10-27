package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;
import java.util.Date;

public class GetGroupPirceDataDTO implements Serializable {
	private Integer productId;
	private Integer supplierId;
	private Date date;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

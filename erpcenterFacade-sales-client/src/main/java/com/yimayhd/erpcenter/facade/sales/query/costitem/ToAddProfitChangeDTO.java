package com.yimayhd.erpcenter.facade.sales.query.costitem;

import java.io.Serializable;
import java.math.BigDecimal;

public class ToAddProfitChangeDTO implements Serializable{
	private Integer id;
	private Integer  creatorId;
	private String  creatorName;
	private BigDecimal  price;
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}

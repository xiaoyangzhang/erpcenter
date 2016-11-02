package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;

public class ProductGroupQueryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private Integer bizId;
	private Integer productId;
	private Integer groupId;
	private String[] idArr;
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String[] getIdArr() {
		return idArr;
	}
	public void setIdArr(String[] idArr) {
		this.idArr = idArr;
	}
	
 }

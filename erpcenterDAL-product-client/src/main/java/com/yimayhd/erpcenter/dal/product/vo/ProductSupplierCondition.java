package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;

public class ProductSupplierCondition implements Serializable {
	private Integer productId;
	private String supplierName;
	private String city;
	/**
	 * 排除掉的组团社id
	 */
	private String outSupplierId;
	private Integer single;
	
	private Integer pageSize;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getOutSupplierId() {
		return outSupplierId;
	}
	public void setOutSupplierId(String outSupplierId) {
		this.outSupplierId = outSupplierId;
	}
	public Integer getSingle() {
		if(single==null || single!=0){
			return 1;
		}
		return single;
	}
	public void setSingle(Integer single) {
		this.single = single;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}

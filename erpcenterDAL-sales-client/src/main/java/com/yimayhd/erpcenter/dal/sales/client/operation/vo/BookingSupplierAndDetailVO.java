package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;

public class BookingSupplierAndDetailVO implements Serializable {
	private Integer supplierId;

	private String supplierName;

	private Integer supplierType;

	private Integer type2Id;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	public Integer getType2Id() {
		return type2Id;
	}

	public void setType2Id(Integer type2Id) {
		this.type2Id = type2Id;
	}
}

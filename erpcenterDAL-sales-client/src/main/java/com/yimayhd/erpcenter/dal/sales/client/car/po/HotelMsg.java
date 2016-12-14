package com.yimayhd.erpcenter.dal.sales.client.car.po;

import java.io.Serializable;

public class HotelMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int supplierId;
	private String supplierName;
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}

package com.yimayhd.erpcenter.dal.sales.client.car.po;

import java.io.Serializable;
import java.util.Date;

public class TransPortHotel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int groupId;
	private int supplierId;
	private String supplierName;
	private Date itemDate;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
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
	public Date getItemDate() {
		return itemDate;
	}
	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}
	
	
	
}
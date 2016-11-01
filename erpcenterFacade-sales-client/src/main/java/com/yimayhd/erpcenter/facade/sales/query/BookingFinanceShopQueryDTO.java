package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpresource.dal.po.SupplierItem;

public class BookingFinanceShopQueryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private Integer supplierId;
	private Integer bizId;
	private String bizCode;
	private Sheet sheet;
	private List<SupplierItem> supplierItems;
	private String userName;
	private Integer userId;
	private String supplierName;
	private Map<String, Object> map;
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	
	public List<SupplierItem> getSupplierItems() {
		return supplierItems;
	}
	public void setSupplierItems(List<SupplierItem> supplierItems) {
		this.supplierItems = supplierItems;
	}
	
}

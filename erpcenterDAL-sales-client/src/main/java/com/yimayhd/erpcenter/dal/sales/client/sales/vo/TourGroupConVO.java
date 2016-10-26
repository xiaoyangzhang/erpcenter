package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.Date;

public class TourGroupConVO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 9122358451361772684L;

	private Integer id ; //订单id
	
	private Integer supplierId ; 

	private Date dateStart ; //开始时间的起始时间
	
	private Date dateStartEnd ; //开始时间的结束时间
	
	private String groupCode ; //团号
	
	private String productBrandName ; //产品名称
	
	private String supplierName ; //组团社 --
	
	private Integer groupState ; //团状态
	
	private Integer days ; //天数 --
	
	private String persons ; //人数 --
	
	private String operatorName ;
	
	private String saleOperatorName ; //销售计调

	private String contactName ;
	
	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSaleOperatorName() {
		return saleOperatorName;
	}

	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}

	public Integer getGroupState() {
		return groupState;
	}

	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateStartEnd() {
		return dateStartEnd;
	}

	public void setDateStartEnd(Date dateStartEnd) {
		this.dateStartEnd = dateStartEnd;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
}

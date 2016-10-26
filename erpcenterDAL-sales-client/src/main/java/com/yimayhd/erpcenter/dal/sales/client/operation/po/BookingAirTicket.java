package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingAirTicket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer bizId;
    private Integer groupId;
    private Integer orderId;
    private String groupCode;
    private String groupOperatorName;
    private Integer groupOperatorId;
    private String orderCode;
    private String productName;
    private String productBrandName;
    private String receiveMode;
    private Integer groupMode; // 0散客, >0团队
    private Integer supplierId;
    private String supplierName;
    private String supplierOrderNo; // booking_no
    private String contact;
    private String contactTel;
    private String contactFax;
    private String contactMobile;
    private String details;
    // 团出发日期，来自group_order
    private Date startDate; 
    // 机票出发日期， 来自booking_supplier_detail的item_date，拷贝自air_ticket_resource的dep_date
    private Date depDate; 
    private String cashType;
    private BigDecimal total;
    private BigDecimal totalCash;
    private Integer stateFinance; //0未审核，1已审核
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
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
	public String getSupplierOrderNo() {
		return supplierOrderNo;
	}
	public void setSupplierOrderNo(String supplierOrderNo) {
		this.supplierOrderNo = supplierOrderNo;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getContactFax() {
		return contactFax;
	}
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDepDate() {
		return depDate;
	}
	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	public Integer getStateFinance() {
		return stateFinance;
	}
	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getGroupOperatorName() {
		return groupOperatorName;
	}
	public void setGroupOperatorName(String groupOperatorName) {
		this.groupOperatorName = groupOperatorName;
	}
	public Integer getGroupOperatorId() {
		return groupOperatorId;
	}
	public void setGroupOperatorId(Integer groupOperatorId) {
		this.groupOperatorId = groupOperatorId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

    
    
}
package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.Date;

public class SalePrice implements Serializable  {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mode;
	private Integer orderId;
	private Integer itemId;
	private String itemName;
	private String remark;
	private Double unitPrice;
	private Double numTimes;
	private Double numPerson;
	private Double totalPrice;
	private Integer rowState;
	private Integer creatorId;
	private String creatorName;
	private Long createTime;
	private Integer priceLockState ;
	private Integer priceType ;
	
	public Integer getPriceType() {
		return priceType;
	}
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}
	private Integer page =1;
	private Integer pageSize =15;
	private String orgIds ;
	private String saleOperatorIds ;
	private String groupMode ;
	
	private String groupCode ;
	private String receiveMode ;
	private Date dateStart ;
	private String operatorName ;
	private Integer numAdult ;
	private Integer numChild ;
	private Integer numGuide ;
	private String supplierName ;
	private String saleOperatorName ;
	private Integer orderType ;
	private String startTime ;
	private String endTime ;
	private String productName ;
	private String productBrandName ;
	
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getSaleOperatorName() {
		return saleOperatorName;
	}
	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getNumTimes() {
		return numTimes;
	}
	public void setNumTimes(Double numTimes) {
		this.numTimes = numTimes;
	}
	public Double getNumPerson() {
		return numPerson;
	}
	public void setNumPerson(Double numPerson) {
		this.numPerson = numPerson;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getRowState() {
		return rowState;
	}
	public void setRowState(Integer rowState) {
		this.rowState = rowState;
	}
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
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getPriceLockState() {
		return priceLockState;
	}
	public void setPriceLockState(Integer priceLockState) {
		this.priceLockState = priceLockState;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public String getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(String groupMode) {
		this.groupMode = groupMode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getNumAdult() {
		return numAdult;
	}
	public void setNumAdult(Integer numAdult) {
		this.numAdult = numAdult;
	}
	public Integer getNumChild() {
		return numChild;
	}
	public void setNumChild(Integer numChild) {
		this.numChild = numChild;
	}
	public Integer getNumGuide() {
		return numGuide;
	}
	public void setNumGuide(Integer numGuide) {
		this.numGuide = numGuide;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
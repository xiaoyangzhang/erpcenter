package com.yimayhd.erpcenter.dal.sales.client.sales.vo;


import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OperatorOrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1694998435804730178L;

	private Integer dateType;
	private String startTime;
	private String endTime;

	private String groupCode;
	private String supplierName;
	private String receiveMode;

	private Integer orderMode;
	private Integer stateFinance;
	private Integer orderLockState;

	private String orgIds;
	private Integer operType;
	private String saleOperatorIds;
	private Integer productBrandId;
	private String productName;
	private Integer pageSize = 15;
	private Integer page = 1;
	
	private String  mobile;
	private String  guestNames;
	private List<GroupOrderGuest> guests = new ArrayList<GroupOrderGuest>() ;
	
	
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGuestNames() {
		return guestNames;
	}

	public void setGuestNames(String guestNames) {
		this.guestNames = guestNames;
	}

	public List<GroupOrderGuest> getGuests() {
		return guests;
	}

	public void setGuests(List<GroupOrderGuest> guests) {
		this.guests = guests;
	}

	private String guestName;
	

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getReceiveMode() {
		return receiveMode;
	}

	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}

	public Integer getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(Integer orderMode) {
		this.orderMode = orderMode;
	}

	public Integer getStateFinance() {
		return stateFinance;
	}

	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}

	public Integer getOrderLockState() {
		return orderLockState;
	}

	public void setOrderLockState(Integer orderLockState) {
		this.orderLockState = orderLockState;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}

	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}

	public Integer getProductBrandId() {
		return productBrandId;
	}

	public void setProductBrandId(Integer productBrandId) {
		this.productBrandId = productBrandId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}

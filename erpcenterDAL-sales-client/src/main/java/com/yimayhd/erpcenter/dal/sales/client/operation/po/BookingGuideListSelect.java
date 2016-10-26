package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 带团统计
 */
public class BookingGuideListSelect implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3891819653689808050L;
	private Integer id;
	private Integer groupId;
	private String groupCode;
	private Integer groupMode;
	private String dateStart;
	private String productName;
	private String productBrandName;
	private String supplierName;
	private Integer adultCount;
	private Integer childCount;
	private Integer guideCount;
	private Integer orderId;
	private String groupStatus;
	private String operatorName;
	private String userName;
	private String guideName;
	private String guideMobile;
	private Integer stateBooking;
	private BigDecimal total;
	private BigDecimal sj;
	private BigDecimal jh;
	private Date startTime;
	private Date endTime;
	private Integer operType;
	private Integer operatorId;
	private Integer saleOperatorId;
	private String saleOperatorIds;
	private String saleOperatorName;
	private String orgNames;
	private String orgIds;

	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
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
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	
	public Integer getStateBooking() {
		return stateBooking;
	}
	public void setStateBooking(Integer stateBooking) {
		this.stateBooking = stateBooking;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getSj() {
		return sj;
	}
	public void setSj(BigDecimal sj) {
		this.sj = sj;
	}
	public BigDecimal getJh() {
		return jh;
	}
	public void setJh(BigDecimal jh) {
		this.jh = jh;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public Integer getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public Integer getGuideCount() {
		return guideCount;
	}
	public void setGuideCount(Integer guideCount) {
		this.guideCount = guideCount;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	public String getGuideMobile() {
		return guideMobile;
	}
	public void setGuideMobile(String guideMobile) {
		this.guideMobile = guideMobile;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Integer getOperatorId() {
		return operatorId;
	}
	
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getSaleOperatorId() {
		return saleOperatorId;
	}

	public void setSaleOperatorId(Integer saleOperatorId) {
		this.saleOperatorId = saleOperatorId;
	}

	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}

	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}

	public String getSaleOperatorName() {
		return saleOperatorName;
	}

	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
}

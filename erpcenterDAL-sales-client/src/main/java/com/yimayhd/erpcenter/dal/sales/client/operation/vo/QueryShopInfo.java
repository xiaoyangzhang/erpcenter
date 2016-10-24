package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class QueryShopInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer groupId;
	private String groupCode;
	private Integer groupMode;
	private Integer orderId;
	private String operatorName;//计调
	private String userName;//导管
	private String guideName;//导游
	private String supplierName;//购物店名称
	private Date shopDate;//进店日期
	private Integer personNum;//进店人数
	private BigDecimal personRepayPrice;//返款单价
	private BigDecimal jhSales;//计划销售
	private BigDecimal totalFace;//实际销售
	private BigDecimal totalRepay;//返款金额
	private Integer bizId;
	private Integer pageSize;
	private Integer page=1;
	private Integer selectDate;//日期类型的选择
	private List<GroupOrder> groupOrders;
	
	public List<GroupOrder> getGroupOrders() {
		return groupOrders;
	}
	public void setGroupOrders(List<GroupOrder> groupOrders) {
		this.groupOrders = groupOrders;
	}
	public Integer getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(Integer selectDate) {
		this.selectDate = selectDate;
	}
	private Date startTime;
	private Date endTime;
	private String saleOperatorIds;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Date getShopDate() {
		return shopDate;
	}
	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}
	public Integer getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	public BigDecimal getPersonRepayPrice() {
		return personRepayPrice;
	}
	public void setPersonRepayPrice(BigDecimal personRepayPrice) {
		this.personRepayPrice = personRepayPrice;
	}
	public BigDecimal getJhSales() {
		return jhSales;
	}
	public void setJhSales(BigDecimal jhSales) {
		this.jhSales = jhSales;
	}
	public BigDecimal getTotalFace() {
		return totalFace;
	}
	public void setTotalFace(BigDecimal totalFace) {
		this.totalFace = totalFace;
	}
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}

	
	
	
	
}

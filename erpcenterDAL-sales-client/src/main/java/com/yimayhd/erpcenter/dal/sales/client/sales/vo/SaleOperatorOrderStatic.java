package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleOperatorOrderStatic implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8039632794680465586L;
	//团号
	private Integer orderNum;
	//总成人数
	private Integer numAdult;
	//总小孩数
	private Integer numChild;
	//总全陪数
	private Integer numGuide;
	//订单开始日期
	private String departureDate;
	
	private String productBrandName; 
	//产品名称 
	private String productName; 
	//进店店收入
	private BigDecimal shopIncome;
	//订单总收入
	private BigDecimal orderIncome;
	private Integer orderType ;
	private Integer sumOrderNum ;
	private Integer sumAdult;
	private Integer sumChild;
	private Integer sumGuide;
	private BigDecimal sumShopIncome ;
	private BigDecimal sumTotalIncome ;
	private BigDecimal sumTotalCost ;
	private BigDecimal sumRayPayIncome ;
	private BigDecimal sumFsIncome ;
	private BigDecimal sumOtherIncome ;
	private BigDecimal sumOrderIncome ;
	private String orgIds ;
	private String saleOperatorIds ;
	private String saleOperatorName;
	private String startTime ;
	private String endTime ;
	private BigDecimal totalCost ;
	private BigDecimal totalPersons ;
	private BigDecimal otherIncome ;
	private BigDecimal rayPayIncome ;
	private BigDecimal fsIncome ;
	private BigDecimal totalIncome ; 
	
	public BigDecimal getSumTotalIncome() {
		return sumTotalIncome;
	}
	public void setSumTotalIncome(BigDecimal sumTotalIncome) {
		this.sumTotalIncome = sumTotalIncome;
	}
	public BigDecimal getSumTotalCost() {
		return sumTotalCost;
	}
	public void setSumTotalCost(BigDecimal sumTotalCost) {
		this.sumTotalCost = sumTotalCost;
	}
	public BigDecimal getSumRayPayIncome() {
		return sumRayPayIncome;
	}
	public void setSumRayPayIncome(BigDecimal sumRayPayIncome) {
		this.sumRayPayIncome = sumRayPayIncome;
	}
	public BigDecimal getSumFsIncome() {
		return sumFsIncome;
	}
	public void setSumFsIncome(BigDecimal sumFsIncome) {
		this.sumFsIncome = sumFsIncome;
	}
	public BigDecimal getSumOtherIncome() {
		return sumOtherIncome;
	}
	public void setSumOtherIncome(BigDecimal sumOtherIncome) {
		this.sumOtherIncome = sumOtherIncome;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getTotalPersons() {
		return totalPersons;
	}
	public void setTotalPersons(BigDecimal totalPersons) {
		this.totalPersons = totalPersons;
	}
	public BigDecimal getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}
	public BigDecimal getRayPayIncome() {
		return rayPayIncome;
	}
	public void setRayPayIncome(BigDecimal rayPayIncome) {
		this.rayPayIncome = rayPayIncome;
	}
	public BigDecimal getFsIncome() {
		return fsIncome;
	}
	public void setFsIncome(BigDecimal fsIncome) {
		this.fsIncome = fsIncome;
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
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getShopIncome() {
		return shopIncome;
	}
	public void setShopIncome(BigDecimal shopIncome) {
		this.shopIncome = shopIncome;
	}
	public BigDecimal getOrderIncome() {
		return orderIncome;
	}
	public void setOrderIncome(BigDecimal orderIncome) {
		this.orderIncome = orderIncome;
	}
	public Integer getSumOrderNum() {
		return sumOrderNum;
	}
	public void setSumOrderNum(Integer sumOrderNum) {
		this.sumOrderNum = sumOrderNum;
	}
	public Integer getSumAdult() {
		return sumAdult;
	}
	public void setSumAdult(Integer sumAdult) {
		this.sumAdult = sumAdult;
	}
	public Integer getSumChild() {
		return sumChild;
	}
	public void setSumChild(Integer sumChild) {
		this.sumChild = sumChild;
	}
	public Integer getSumGuide() {
		return sumGuide;
	}
	public void setSumGuide(Integer sumGuide) {
		this.sumGuide = sumGuide;
	}
	public BigDecimal getSumShopIncome() {
		return sumShopIncome;
	}
	public void setSumShopIncome(BigDecimal sumShopIncome) {
		this.sumShopIncome = sumShopIncome;
	}
	public BigDecimal getSumOrderIncome() {
		return sumOrderIncome;
	}
	public void setSumOrderIncome(BigDecimal sumOrderIncome) {
		this.sumOrderIncome = sumOrderIncome;
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
}

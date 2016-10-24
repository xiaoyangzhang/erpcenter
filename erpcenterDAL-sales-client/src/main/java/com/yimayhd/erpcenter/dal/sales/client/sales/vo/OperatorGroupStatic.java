package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OperatorGroupStatic implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8039632794680465586L;
	//团号
	private Integer groupNum;
	//操作计调
	private String operatorName;
	//总成人数
	private Integer totalAdult;
	//总小孩数
	private Integer totalChild;
	//总全陪数
	private Integer totalGuide;
	//团开始日期
	private String dateStart;
	//团类别
	private Integer groupMode;
	//产品品牌 
	private String productBrandName; 
	//产品名称 
	private String productName; 
	//团款收入 
	private BigDecimal groupIncome; 
	//其他收入
	private BigDecimal otherIncome;
	//进店店收入
	private BigDecimal shopTotalIncome;
	//团总成本
	private BigDecimal groupCost;
	//团总收入
	private BigDecimal totalIncome;
	
	private Integer sumGroupNum ;
	private Integer sumAdult;
	private Integer sumChild;
	private Integer sumGuide;
	private BigDecimal sumGroupIncome;
	private BigDecimal sumOtherIncome;
	private BigDecimal sumShopTotalIncome;
	private BigDecimal sumGroupCost;
	private BigDecimal sumTotalIncome ;
	private String orgIds ;
	private String operatorIds ;
	private String startTime ;
	private String endTime ;
	
	public Integer getSumGroupNum() {
		return sumGroupNum;
	}
	public void setSumGroupNum(Integer sumGroupNum) {
		this.sumGroupNum = sumGroupNum;
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
	public BigDecimal getSumGroupIncome() {
		return sumGroupIncome;
	}
	public void setSumGroupIncome(BigDecimal sumGroupIncome) {
		this.sumGroupIncome = sumGroupIncome;
	}
	public BigDecimal getSumOtherIncome() {
		return sumOtherIncome;
	}
	public void setSumOtherIncome(BigDecimal sumOtherIncome) {
		this.sumOtherIncome = sumOtherIncome;
	}
	public BigDecimal getSumShopTotalIncome() {
		return sumShopTotalIncome;
	}
	public void setSumShopTotalIncome(BigDecimal sumShopTotalIncome) {
		this.sumShopTotalIncome = sumShopTotalIncome;
	}
	public BigDecimal getSumGroupCost() {
		return sumGroupCost;
	}
	public void setSumGroupCost(BigDecimal sumGroupCost) {
		this.sumGroupCost = sumGroupCost;
	}
	public BigDecimal getSumTotalIncome() {
		return sumTotalIncome;
	}
	public void setSumTotalIncome(BigDecimal sumTotalIncome) {
		this.sumTotalIncome = sumTotalIncome;
	}
	
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
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
	public Integer getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getTotalAdult() {
		return totalAdult;
	}
	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}
	public Integer getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}
	public Integer getTotalGuide() {
		return totalGuide;
	}
	public void setTotalGuide(Integer totalGuide) {
		this.totalGuide = totalGuide;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
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
	public BigDecimal getGroupIncome() {
		return groupIncome;
	}
	public void setGroupIncome(BigDecimal groupIncome) {
		this.groupIncome = groupIncome;
	}
	public BigDecimal getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}
	public BigDecimal getShopTotalIncome() {
		return shopTotalIncome;
	}
	public void setShopTotalIncome(BigDecimal shopTotalIncome) {
		this.shopTotalIncome = shopTotalIncome;
	}
	public BigDecimal getGroupCost() {
		return groupCost;
	}
	public void setGroupCost(BigDecimal groupCost) {
		this.groupCost = groupCost;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
}

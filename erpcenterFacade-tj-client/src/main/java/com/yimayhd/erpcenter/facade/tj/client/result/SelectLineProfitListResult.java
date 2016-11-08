package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yihg.mybatis.utility.PageBean;

public class SelectLineProfitListResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	private BigDecimal allIncomeOrder;
	private BigDecimal allIncomeOther;
	private BigDecimal allShopRepay;
	private BigDecimal allTotalCost;
	private BigDecimal allShopCommission;
	private BigDecimal allTotalProfit;
	private BigDecimal allShopSales;
	private BigDecimal allSumPerson;
	private BigDecimal allTotalAdult;
	private BigDecimal allTotalChild;
	private BigDecimal allTotalGuide;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public BigDecimal getAllIncomeOrder() {
		return allIncomeOrder;
	}
	public void setAllIncomeOrder(BigDecimal allIncomeOrder) {
		this.allIncomeOrder = allIncomeOrder;
	}
	public BigDecimal getAllIncomeOther() {
		return allIncomeOther;
	}
	public void setAllIncomeOther(BigDecimal allIncomeOther) {
		this.allIncomeOther = allIncomeOther;
	}
	public BigDecimal getAllShopRepay() {
		return allShopRepay;
	}
	public void setAllShopRepay(BigDecimal allShopRepay) {
		this.allShopRepay = allShopRepay;
	}
	public BigDecimal getAllTotalCost() {
		return allTotalCost;
	}
	public void setAllTotalCost(BigDecimal allTotalCost) {
		this.allTotalCost = allTotalCost;
	}
	public BigDecimal getAllShopCommission() {
		return allShopCommission;
	}
	public void setAllShopCommission(BigDecimal allShopCommission) {
		this.allShopCommission = allShopCommission;
	}
	public BigDecimal getAllTotalProfit() {
		return allTotalProfit;
	}
	public void setAllTotalProfit(BigDecimal allTotalProfit) {
		this.allTotalProfit = allTotalProfit;
	}
	public BigDecimal getAllShopSales() {
		return allShopSales;
	}
	public void setAllShopSales(BigDecimal allShopSales) {
		this.allShopSales = allShopSales;
	}
	public BigDecimal getAllSumPerson() {
		return allSumPerson;
	}
	public void setAllSumPerson(BigDecimal allSumPerson) {
		this.allSumPerson = allSumPerson;
	}
	public BigDecimal getAllTotalAdult() {
		return allTotalAdult;
	}
	public void setAllTotalAdult(BigDecimal allTotalAdult) {
		this.allTotalAdult = allTotalAdult;
	}
	public BigDecimal getAllTotalChild() {
		return allTotalChild;
	}
	public void setAllTotalChild(BigDecimal allTotalChild) {
		this.allTotalChild = allTotalChild;
	}
	public BigDecimal getAllTotalGuide() {
		return allTotalGuide;
	}
	public void setAllTotalGuide(BigDecimal allTotalGuide) {
		this.allTotalGuide = allTotalGuide;
	}
	
	
	

	
}

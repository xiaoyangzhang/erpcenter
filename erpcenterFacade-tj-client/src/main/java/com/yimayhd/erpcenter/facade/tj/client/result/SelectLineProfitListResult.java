package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;

public class SelectLineProfitListResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	private String allIncomeOrder;
	private String allIncomeOther;
	private String allShopRepay;
	private String allTotalCost;
	private String allShopCommission;
	private String allTotalProfit;
	private String allShopSales;
	private String allSumPerson;
	private String allTotalAdult;
	private String allTotalChild;
	private String allTotalGuide;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String getAllIncomeOrder() {
		return allIncomeOrder;
	}
	public void setAllIncomeOrder(String allIncomeOrder) {
		this.allIncomeOrder = allIncomeOrder;
	}
	public String getAllIncomeOther() {
		return allIncomeOther;
	}
	public void setAllIncomeOther(String allIncomeOther) {
		this.allIncomeOther = allIncomeOther;
	}
	public String getAllShopRepay() {
		return allShopRepay;
	}
	public void setAllShopRepay(String allShopRepay) {
		this.allShopRepay = allShopRepay;
	}
	public String getAllTotalCost() {
		return allTotalCost;
	}
	public void setAllTotalCost(String allTotalCost) {
		this.allTotalCost = allTotalCost;
	}
	public String getAllShopCommission() {
		return allShopCommission;
	}
	public void setAllShopCommission(String allShopCommission) {
		this.allShopCommission = allShopCommission;
	}
	public String getAllTotalProfit() {
		return allTotalProfit;
	}
	public void setAllTotalProfit(String allTotalProfit) {
		this.allTotalProfit = allTotalProfit;
	}
	public String getAllShopSales() {
		return allShopSales;
	}
	public void setAllShopSales(String allShopSales) {
		this.allShopSales = allShopSales;
	}
	public String getAllSumPerson() {
		return allSumPerson;
	}
	public void setAllSumPerson(String allSumPerson) {
		this.allSumPerson = allSumPerson;
	}
	public String getAllTotalAdult() {
		return allTotalAdult;
	}
	public void setAllTotalAdult(String allTotalAdult) {
		this.allTotalAdult = allTotalAdult;
	}
	public String getAllTotalChild() {
		return allTotalChild;
	}
	public void setAllTotalChild(String allTotalChild) {
		this.allTotalChild = allTotalChild;
	}
	public String getAllTotalGuide() {
		return allTotalGuide;
	}
	public void setAllTotalGuide(String allTotalGuide) {
		this.allTotalGuide = allTotalGuide;
	}
	

	
}

package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

/**
 * 
 * @author qindz
 *
 */
public class TourGroupPriceAndPersons implements Serializable {

	private static final long serialVersionUID = 7013541346760126948L;

	private Integer totalAdult; //成人数

	private Integer totalChild; //小孩数

	private Integer totalGuide; //全陪数
	
	private Double CostTotalPrice ;  //成本
	
	private Double IncomeIncome ; // 收入
	
	private Double profit;//预计利润

	private Double totalProfit;//预计人均利润
	
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
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

	public Double getCostTotalPrice() {
		return CostTotalPrice;
	}

	public void setCostTotalPrice(Double costTotalPrice) {
		CostTotalPrice = costTotalPrice;
	}

	public Double getIncomeIncome() {
		return IncomeIncome;
	}

	public void setIncomeIncome(Double incomeIncome) {
		IncomeIncome = incomeIncome;
	}

	@Override
	public String toString() {
		return "TourGroupPriceAndPersons [totalAdult=" + totalAdult
				+ ", totalChild=" + totalChild + ", totalGuide=" + totalGuide
				+ ", CostTotalPrice=" + CostTotalPrice + ", IncomeIncome="
				+ IncomeIncome + "]";
	}
}

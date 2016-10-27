package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.CostIncome;

public class ToSaveCostIncomeDTO implements Serializable {
	
	private static final long serialVersionUID = -2703943094331322910L;
	private CostIncome costIncome;
	private Integer employeeId;
	private String curUserName;

	public CostIncome getCostIncome() {
		return costIncome;
	}

	public void setCostIncome(CostIncome costIncome) {
		this.costIncome = costIncome;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}
}

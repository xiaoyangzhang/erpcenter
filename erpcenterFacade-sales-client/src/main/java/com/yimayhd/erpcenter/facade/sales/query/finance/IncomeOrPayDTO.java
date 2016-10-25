package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;


public class IncomeOrPayDTO implements Serializable{
    private static final long serialVersionUID = -5738945525593633404L;
    
    private Integer payId;
	private int bizId;
    private PlatformEmployeePo employee;
    
    public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public PlatformEmployeePo getEmployee() {
		return employee;
	}
	public void setEmployee(PlatformEmployeePo employee) {
		this.employee = employee;
	}
}
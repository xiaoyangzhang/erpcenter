package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;


public class MakeCollectionsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FinancePay pay;
	
	private Integer orderId;
	
	private int bizId;
	private PlatformEmployeePo emp;

	public FinancePay getPay() {
		return pay;
	}

	public void setPay(FinancePay pay) {
		this.pay = pay;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public int getBizId() {
		return bizId;
	}

	public void setBizId(int bizId) {
		this.bizId = bizId;
	}

	public PlatformEmployeePo getEmp() {
		return emp;
	}

	public void setEmp(PlatformEmployeePo emp) {
		this.emp = emp;
	}
	
	
		
}

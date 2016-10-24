package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinancePayDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4634259410931573630L;

	private Integer id;

	private Integer payId;

	private Integer locOrderId;

	private BigDecimal cash;
	
	private String payDate;

	private String payType;
	
	private String userName;
	
	private String remark;
	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getLocOrderId() {
		return locOrderId;
	}

	public void setLocOrderId(Integer locOrderId) {
		this.locOrderId = locOrderId;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
}
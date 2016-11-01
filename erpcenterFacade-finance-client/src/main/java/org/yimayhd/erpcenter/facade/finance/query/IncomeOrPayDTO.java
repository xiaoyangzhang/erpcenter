package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;


public class IncomeOrPayDTO implements Serializable{
    private static final long serialVersionUID = -5738945525593633404L;
    
    private Integer payId;
	private int bizId;
    
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
}
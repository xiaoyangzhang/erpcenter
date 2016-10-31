package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;

public class PayGuideBillPlusDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private FinancePay pay;
	private String checkedArr;
	
	public FinancePay getPay() {
		return pay;
	}
	public void setPay(FinancePay pay) {
		this.pay = pay;
	}
	public String getCheckedArr() {
		return checkedArr;
	}
	public void setCheckedArr(String checkedArr) {
		this.checkedArr = checkedArr;
	}
	
	
}

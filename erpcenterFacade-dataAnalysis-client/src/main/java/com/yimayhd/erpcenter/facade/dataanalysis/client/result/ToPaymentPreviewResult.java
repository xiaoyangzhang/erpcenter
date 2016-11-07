package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;

public class ToPaymentPreviewResult extends BaseResult {
	private static final long serialVersionUID = -8139244338093221977L;
	private List<GroupOrder> orders;
	private Map parameters;

	private GroupOrder orderMiddle;
	private GroupOrder orderPre;

	private String sb1;
	private String sb2;
	private List<SysBizBankAccount> bankAccounts;
	
	public List<SysBizBankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<SysBizBankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	private List<FinancePay> payDetailList;

	public List<GroupOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public GroupOrder getOrderMiddle() {
		return orderMiddle;
	}

	public void setOrderMiddle(GroupOrder orderMiddle) {
		this.orderMiddle = orderMiddle;
	}

	public GroupOrder getOrderPre() {
		return orderPre;
	}

	public void setOrderPre(GroupOrder orderPre) {
		this.orderPre = orderPre;
	}

	public String getSb1() {
		return sb1;
	}

	public void setSb1(String sb1) {
		this.sb1 = sb1;
	}

	public List<FinancePay> getPayDetailList() {
		return payDetailList;
	}

	public void setPayDetailList(List<FinancePay> payDetailList) {
		this.payDetailList = payDetailList;
	}

	public String getSb2() {
		return sb2;
	}

	public void setSb2(String sb2) {
		this.sb2 = sb2;
	}
}

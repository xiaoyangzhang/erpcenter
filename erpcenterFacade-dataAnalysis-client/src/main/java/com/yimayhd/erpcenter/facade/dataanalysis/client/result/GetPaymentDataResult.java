package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;

public class GetPaymentDataResult extends BaseResult {
	private static final long serialVersionUID = 7042121642898539483L;

	private List<SysBizBankAccount> sysBizBankAccountList;

	private List<FinancePay> payDetailList;
	private List<GroupOrder> orders;

	private GroupOrder orderMiddle;
	private GroupOrder orderPre;

	public List<FinancePay> getPayDetailList() {
		return payDetailList;
	}

	public void setPayDetailList(List<FinancePay> payDetailList) {
		this.payDetailList = payDetailList;
	}

	public List<GroupOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<GroupOrder> orders) {
		this.orders = orders;
	}

	public List<SysBizBankAccount> getSysBizBankAccountList() {
		return sysBizBankAccountList;
	}

	public void setSysBizBankAccountList(List<SysBizBankAccount> sysBizBankAccountList) {
		this.sysBizBankAccountList = sysBizBankAccountList;
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
}

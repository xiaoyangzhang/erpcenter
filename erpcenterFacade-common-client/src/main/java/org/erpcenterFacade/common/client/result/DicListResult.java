package org.erpcenterFacade.common.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;

public class DicListResult implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private List<DicInfo> bankList;
	private List<SysBizBankAccount> bizAccountList;
	private List<DicInfo> payChannels;
	private List<DicInfo> commissionTypes;
	
	public List<DicInfo> getCommissionTypes() {
		return commissionTypes;
	}
	public void setCommissionTypes(List<DicInfo> commissionTypes) {
		this.commissionTypes = commissionTypes;
	}
	public List<DicInfo> getBankList() {
		return bankList;
	}
	public void setBankList(List<DicInfo> bankList) {
		this.bankList = bankList;
	}
	public List<SysBizBankAccount> getBizAccountList() {
		return bizAccountList;
	}
	public void setBizAccountList(List<SysBizBankAccount> bizAccountList) {
		this.bizAccountList = bizAccountList;
	}
	public List<DicInfo> getPayChannels() {
		return payChannels;
	}
	public void setPayChannels(List<DicInfo> payChannels) {
		this.payChannels = payChannels;
	}
	
}

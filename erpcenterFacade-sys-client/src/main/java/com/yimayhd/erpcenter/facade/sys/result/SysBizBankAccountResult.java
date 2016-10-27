package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
/**
 * 
 * 描述：银行信息封装
 * @author liyong
 * 2016年10月21日
 */
public class SysBizBankAccountResult extends ResultSupport implements Serializable {

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysBizBankAccount sysBizBankAccount = new SysBizBankAccount();
	private List<DicInfo> bankList;
	public SysBizBankAccount getSysBizBankAccount() {
		return sysBizBankAccount;
	}

	public void setSysBizBankAccount(SysBizBankAccount sysBizBankAccount) {
		this.sysBizBankAccount = sysBizBankAccount;
	}

	public List<DicInfo> getBankList() {
		return bankList;
	}

	public void setBankList(List<DicInfo> bankList) {
		this.bankList = bankList;
	}

	
	
}

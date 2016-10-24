package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
/**
 * 
 * 描述：银行信息参数封装
 * @author liyong
 * 2016年10月21日
 */
public class SysBizBankAccountDTO implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private SysBizBankAccount sysBizBankAccount = new SysBizBankAccount();

	public SysBizBankAccount getSysBizBankAccount() {
		return sysBizBankAccount;
	}

	public void setSysBizBankAccount(SysBizBankAccount sysBizBankAccount) {
		this.sysBizBankAccount = sysBizBankAccount;
	}
	
	

}

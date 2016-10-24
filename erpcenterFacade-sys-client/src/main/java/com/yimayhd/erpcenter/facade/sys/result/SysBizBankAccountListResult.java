package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @author liyong
 * 2016年10月21日
 */
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
public class SysBizBankAccountListResult extends ResultSupport implements Serializable{

	/**
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SysBizBankAccount> sysBizBankAccounts = new ArrayList<SysBizBankAccount>();

	public List<SysBizBankAccount> getSysBizBankAccounts() {
		return sysBizBankAccounts;
	}

	public void setSysBizBankAccounts(List<SysBizBankAccount> sysBizBankAccounts) {
		this.sysBizBankAccounts = sysBizBankAccounts;
	}
	
	

}

package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpcenter.facade.sys.query.SysBizBankAccountDTO;
import com.yimayhd.erpcenter.facade.sys.result.SysBizBankAccountListResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizBankAccountResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizConfigListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysBizBankAccountFacade;


public class SysBizBankAccountFacadeImpl implements SysBizBankAccountFacade {
	@Autowired
	private SysBizBankAccountBiz sysBizBankAccountBiz;
		
	@Override
	public SysBizBankAccountListResult getListByBizId(Integer bizId) {
		SysBizBankAccountListResult result = new SysBizBankAccountListResult();
		List<SysBizBankAccount> sysBizBankAccounts = sysBizBankAccountBiz.getListByBizId(bizId);
		if(sysBizBankAccounts!=null)
			result.setSysBizBankAccounts(sysBizBankAccounts);
		return result;
		 
	}

	@Override
	public void delBankAccount(Integer id) {
		sysBizBankAccountBiz.delBankAccount(id);
	}

	@Override
	public int addSysBizBankAccount(SysBizBankAccountDTO bankAcc) {
		
		return sysBizBankAccountBiz.addSysBizBankAccount(bankAcc.getSysBizBankAccount());
	}

	@Override
	public SysBizBankAccountResult getBankInfo(Integer id) {
		SysBizBankAccountResult result = new SysBizBankAccountResult();
		SysBizBankAccount  sysBizBankAccount= sysBizBankAccountBiz.getBankInfo(id);
		if(sysBizBankAccount!=null)
			result.setSysBizBankAccount(sysBizBankAccount);
		return result;
	}

	@Override
	public void updateSysBizBankAccount(SysBizBankAccountDTO bankAcc) {
		sysBizBankAccountBiz.updateSysBizBankAccount(bankAcc.getSysBizBankAccount());
	}

}

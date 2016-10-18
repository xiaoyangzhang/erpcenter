package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;


public class SysBizBankAccountBizImpl implements SysBizBankAccountBiz {
	@Autowired
	private com.yimayhd.erpcenter.dal.sys.service.SysBizBankAccountDal sysBizBankAccountDal;
		
	@Override
	public List<SysBizBankAccount> getListByBizId(Integer bizId) {
		return sysBizBankAccountDal.getListByBizId(bizId);
	}

	@Override
	public void delBankAccount(Integer id) {
		sysBizBankAccountDal.delBankAccount(id);
	}

	@Override
	public int addSysBizBankAccount(SysBizBankAccount bankAcc) {
		return sysBizBankAccountDal.addSysBizBankAccount(bankAcc);
	}

	@Override
	public SysBizBankAccount getBankInfo(Integer id) {
		return sysBizBankAccountDal.getBankInfo(id);
	}

	@Override
	public void updateSysBizBankAccount(SysBizBankAccount bankAcc) {
		sysBizBankAccountDal.updateSysBizBankAccount(bankAcc);
	}

}

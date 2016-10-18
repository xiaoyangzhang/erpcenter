package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;

public interface SysBizBankAccountDal {
	List<SysBizBankAccount> getListByBizId(Integer bizId);
	void delBankAccount(Integer id);
	int addSysBizBankAccount(SysBizBankAccount bankAcc);
	SysBizBankAccount getBankInfo(Integer id);
	void updateSysBizBankAccount(SysBizBankAccount bankAcc);
}

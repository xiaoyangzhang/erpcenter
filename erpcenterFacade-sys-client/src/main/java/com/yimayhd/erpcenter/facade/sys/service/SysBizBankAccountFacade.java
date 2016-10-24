package com.yimayhd.erpcenter.facade.sys.service;

import com.yimayhd.erpcenter.facade.sys.query.SysBizBankAccountDTO;
import com.yimayhd.erpcenter.facade.sys.result.SysBizBankAccountListResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizBankAccountResult;

public interface SysBizBankAccountFacade {
	SysBizBankAccountListResult getListByBizId(Integer bizId);
	void delBankAccount(Integer id);
	int addSysBizBankAccount(SysBizBankAccountDTO bankAcc);
	SysBizBankAccountResult getBankInfo(Integer id);
	void updateSysBizBankAccount(SysBizBankAccountDTO bankAcc);
}

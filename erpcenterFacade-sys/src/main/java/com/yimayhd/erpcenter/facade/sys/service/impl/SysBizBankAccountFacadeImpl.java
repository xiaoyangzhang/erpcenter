package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpcenter.facade.sys.query.SysBizBankAccountDTO;
import com.yimayhd.erpcenter.facade.sys.result.SysBizBankAccountListResult;
import com.yimayhd.erpcenter.facade.sys.result.SysBizBankAccountResult;
import com.yimayhd.erpcenter.facade.sys.service.SysBizBankAccountFacade;


public class SysBizBankAccountFacadeImpl implements SysBizBankAccountFacade {
	@Autowired
	private SysBizBankAccountBiz sysBizBankAccountBiz;
	@Autowired
	private DicBiz dicBiz;
		
	@Override
	public SysBizBankAccountListResult getListByBizId(Integer bizId) {
		SysBizBankAccountListResult result = new SysBizBankAccountListResult();
		List<SysBizBankAccount> sysBizBankAccounts = sysBizBankAccountBiz.getListByBizId(bizId);
		if(sysBizBankAccounts!=null){
			result.setSysBizBankAccounts(sysBizBankAccounts);
		}
		List<DicInfo> bankList = dicBiz
				.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
		result.setBankList(bankList);
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
		if(sysBizBankAccount!=null){
			result.setSysBizBankAccount(sysBizBankAccount);
		}
		List<DicInfo> bankList = dicBiz
				.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
		result.setBankList(bankList);
		return result;
	}

	@Override
	public void updateSysBizBankAccount(SysBizBankAccountDTO bankAcc) {
		sysBizBankAccountBiz.updateSysBizBankAccount(bankAcc.getSysBizBankAccount());
	}

}

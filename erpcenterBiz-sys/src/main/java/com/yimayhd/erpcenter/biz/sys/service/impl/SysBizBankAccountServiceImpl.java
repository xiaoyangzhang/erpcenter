package com.yihg.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.sys.api.SysBizBankAccountService;
import com.yihg.sys.dao.SysBizBankAccountMapper;
import com.yihg.sys.po.SysBizBankAccount;

public class SysBizBankAccountServiceImpl implements SysBizBankAccountService {
	@Autowired
	private SysBizBankAccountMapper bankAccountDao;
		
	@Override
	public List<SysBizBankAccount> getListByBizId(Integer bizId) {
		return bankAccountDao.selectByBizId(bizId);
	}

	@Override
	public void delBankAccount(Integer id) {
		bankAccountDao.deleteByPrimaryKey(id);
	}

	@Override
	public int addSysBizBankAccount(SysBizBankAccount bankAcc) {
		if(bankAcc.getId()==null){
			return bankAccountDao.insertSelective(bankAcc);
		}else{
			return bankAccountDao.updateByPrimaryKey(bankAcc);
		}
	}

	@Override
	public SysBizBankAccount getBankInfo(Integer id) {
		return bankAccountDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateSysBizBankAccount(SysBizBankAccount bankAcc) {
		bankAccountDao.updateByPrimaryKeySelective(bankAcc);
	}

}

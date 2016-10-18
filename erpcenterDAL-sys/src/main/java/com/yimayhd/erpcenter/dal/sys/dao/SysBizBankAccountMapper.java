package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;


public interface SysBizBankAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBizBankAccount record);

    int insertSelective(SysBizBankAccount record);

    SysBizBankAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysBizBankAccount record);

    int updateByPrimaryKey(SysBizBankAccount record);
    
    List<SysBizBankAccount> selectByBizId(Integer bizId);
}
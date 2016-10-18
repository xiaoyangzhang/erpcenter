package com.yimayhd.erpcenter.dal.sys.dao;

import com.yimayhd.erpcenter.dal.sys.po.SettleQualification;

public interface SettleQualificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettleQualification record);

    int insertSelective(SettleQualification record);

    SettleQualification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettleQualification record);

    int updateByPrimaryKey(SettleQualification record);
}
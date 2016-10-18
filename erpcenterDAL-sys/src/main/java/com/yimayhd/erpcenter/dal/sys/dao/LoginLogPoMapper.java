package com.yimayhd.erpcenter.dal.sys.dao;

import com.yimayhd.erpcenter.dal.sys.po.LoginLogPo;

public interface LoginLogPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLogPo record);

    int insertSelective(LoginLogPo record);

    LoginLogPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLogPo record);

    int updateByPrimaryKey(LoginLogPo record);
}
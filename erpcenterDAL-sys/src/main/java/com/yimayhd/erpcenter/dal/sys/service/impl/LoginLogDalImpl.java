package com.yimayhd.erpcenter.dal.sys.service.impl;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.dal.sys.dao.LoginLogPoMapper;
import com.yimayhd.erpcenter.dal.sys.po.LoginLogPo;
import com.yimayhd.erpcenter.dal.sys.service.LoginLogDal;

public class LoginLogDalImpl implements LoginLogDal {

	@Resource
	private LoginLogPoMapper logMapper;
	
	@Override
	public int addLoginLog(LoginLogPo logPo) {
		return logMapper.insert(logPo);
	}

}

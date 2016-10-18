package com.yimayhd.erpcenter.biz.sys.service.impl;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.biz.sys.service.LoginLogBiz;
import com.yimayhd.erpcenter.dal.sys.po.LoginLogPo;
import com.yimayhd.erpcenter.dal.sys.service.LoginLogDal;


public class LoginLogBizImpl implements LoginLogBiz {

	@Resource
	private LoginLogDal loginDal;
	
	@Override
	public int addLoginLog(LoginLogPo logPo) {
		return loginDal.addLoginLog(logPo);
	}

}

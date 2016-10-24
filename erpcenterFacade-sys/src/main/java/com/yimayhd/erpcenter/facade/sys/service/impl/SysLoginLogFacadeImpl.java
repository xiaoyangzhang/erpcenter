package com.yimayhd.erpcenter.facade.sys.service.impl;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.biz.sys.service.LoginLogBiz;
import com.yimayhd.erpcenter.facade.sys.query.LoginLogPoDTO;
import com.yimayhd.erpcenter.facade.sys.service.SysLoginLogFacade;


public class SysLoginLogFacadeImpl implements SysLoginLogFacade {

	@Resource
	private LoginLogBiz loginLogBiz;
	
	@Override
	public int addLoginLog(LoginLogPoDTO logPoDto) {
		
		return loginLogBiz.addLoginLog(logPoDto.getLoginLogPo());
	}

}

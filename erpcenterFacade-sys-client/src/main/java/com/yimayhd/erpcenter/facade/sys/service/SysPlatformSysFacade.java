package com.yimayhd.erpcenter.facade.sys.service;

import com.yimayhd.erpcenter.facade.sys.result.PlatformSysPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformSysPoResult;

public interface SysPlatformSysFacade {
	
	public PlatformSysPoListResult getPlatformSysList();
	
	public PlatformSysPoResult findByCode(String code);
}

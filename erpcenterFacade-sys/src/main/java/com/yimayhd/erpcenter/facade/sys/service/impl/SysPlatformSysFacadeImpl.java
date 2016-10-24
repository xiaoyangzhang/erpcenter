package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatformSysBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformSysDal;
import com.yimayhd.erpcenter.facade.sys.result.PlatformSysPoListResult;
import com.yimayhd.erpcenter.facade.sys.result.PlatformSysPoResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatformSysFacade;

public class SysPlatformSysFacadeImpl implements SysPlatformSysFacade {
	
	private static final Logger logger=LoggerFactory.getLogger(SysPlatformSysFacadeImpl.class);

	@Autowired
	private PlatformSysBiz platformSysBiz;
	
	@Override
	public PlatformSysPoListResult getPlatformSysList() {
		List<PlatformSysPo> platformSysPos  = platformSysBiz.getPlatformSysList();
		PlatformSysPoListResult result = new PlatformSysPoListResult();
		if(platformSysPos!=null)
			result.setPlatformSysPos(platformSysPos);
		return result;
	}

	@Override
	public PlatformSysPoResult findByCode(String code) {
		PlatformSysPo platformSysPo = platformSysBiz.findByCode(code); 
		PlatformSysPoResult result = new PlatformSysPoResult();
		if(platformSysPo!=null)
			result.setPlatformSysPo(platformSysPo);
		
		return result; 
	}

}

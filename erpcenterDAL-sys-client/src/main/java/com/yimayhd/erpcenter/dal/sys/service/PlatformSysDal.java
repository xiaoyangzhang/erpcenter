package com.yimayhd.erpcenter.dal.sys.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;

public interface PlatformSysDal {
	
	public List<PlatformSysPo> getPlatformSysList();
	
	public PlatformSysPo findByCode(String code);
}

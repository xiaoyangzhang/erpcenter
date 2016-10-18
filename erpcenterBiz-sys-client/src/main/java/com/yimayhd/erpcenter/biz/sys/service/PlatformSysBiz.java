package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;

public interface PlatformSysBiz {
	
	public List<PlatformSysPo> getPlatformSysList();
	
	public PlatformSysPo findByCode(String code);
}

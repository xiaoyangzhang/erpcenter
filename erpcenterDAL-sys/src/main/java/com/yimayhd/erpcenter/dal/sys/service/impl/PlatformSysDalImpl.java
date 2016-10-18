package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sys.dao.PlatformSysMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformSysDal;

public class PlatformSysDalImpl implements PlatformSysDal {
	
	private static final Logger logger=LoggerFactory.getLogger(PlatformSysDalImpl.class);

	@Autowired
	private PlatformSysMapper platformSysMapper;
	
	@Override
	public List<PlatformSysPo> getPlatformSysList() {
		return platformSysMapper.getPlatformSysList();
	}

	@Override
	public PlatformSysPo findByCode(String code) {
		return platformSysMapper.findByCode(code); 
	}

}

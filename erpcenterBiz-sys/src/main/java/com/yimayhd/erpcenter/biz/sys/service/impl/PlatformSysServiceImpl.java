package com.yihg.sys.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.sys.api.PlatformSysService;
import com.yihg.sys.dao.PlatformSysMapper;
import com.yihg.sys.po.PlatformSysPo;

public class PlatformSysServiceImpl implements PlatformSysService {
	
	private static final Logger logger=LoggerFactory.getLogger(PlatformSysServiceImpl.class);

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

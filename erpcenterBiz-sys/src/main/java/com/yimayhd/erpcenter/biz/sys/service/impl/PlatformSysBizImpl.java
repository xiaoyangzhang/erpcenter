package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sys.service.PlatformSysBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;
import com.yimayhd.erpcenter.dal.sys.service.PlatformSysDal;

public class PlatformSysBizImpl implements PlatformSysBiz {
	
	private static final Logger logger=LoggerFactory.getLogger(PlatformSysBizImpl.class);

	@Autowired
	private PlatformSysDal platformSysDal;
	
	@Override
	public List<PlatformSysPo> getPlatformSysList() {
		return platformSysDal.getPlatformSysList();
	}

	@Override
	public PlatformSysPo findByCode(String code) {
		return platformSysDal.findByCode(code); 
	}

}

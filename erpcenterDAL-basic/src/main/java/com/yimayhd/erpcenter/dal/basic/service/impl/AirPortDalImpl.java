package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.basic.dao.AirPortMapper;
import com.yimayhd.erpcenter.dal.basic.po.AirPort;
import com.yimayhd.erpcenter.dal.basic.service.AirPortDal;

public class AirPortDalImpl implements AirPortDal {
	@Autowired
	private AirPortMapper airPortMapper;

	@Override
	@Transactional
	public int save(AirPort airPort) {
		return airPortMapper.insertSelective(airPort);
	}

	@Override
	public AirPort selectByPrimaryKey(Integer key) {
		return airPortMapper.selectByPrimaryKey(key);
	}

	@Override
	public String getCityCodeByCityName(String cityName) {
		if(StringUtils.isBlank(cityName)){
			return null;
		}
		return airPortMapper.getCityCodeByCityName(cityName);
	}

	@Override
	public List<Map<String, String>> getFuzzySearchList(String cityName) {
		if(StringUtils.isBlank(cityName)){
			return null;
		}
		return airPortMapper.getFuzzySearchList(cityName);
	}
	
	
}

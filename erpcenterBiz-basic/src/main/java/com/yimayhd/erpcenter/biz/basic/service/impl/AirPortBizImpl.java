package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.AirPortBiz;
import com.yimayhd.erpcenter.dal.basic.po.AirPort;
import com.yimayhd.erpcenter.dal.basic.service.AirPortDal;

public class AirPortBizImpl implements AirPortBiz {
	@Autowired
	private AirPortDal airPorDal;
	@Override
	public int save(AirPort airPort) {
		return airPorDal.save(airPort);
	}

	@Override
	public AirPort selectByPrimaryKey(Integer key) {
		return airPorDal.selectByPrimaryKey(key);
	}

	@Override
	public String getCityCodeByCityName(String cityName) {
		
		return airPorDal.getCityCodeByCityName(cityName);
	}

	@Override
	public List<Map<String, String>> getFuzzySearchList(String cityName) {
		return airPorDal.getFuzzySearchList(cityName);
	}
}

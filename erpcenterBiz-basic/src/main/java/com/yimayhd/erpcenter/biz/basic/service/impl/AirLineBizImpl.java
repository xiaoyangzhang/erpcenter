package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.AirLineBiz;
import com.yimayhd.erpcenter.dal.basic.po.AirLine;
import com.yimayhd.erpcenter.dal.basic.service.AirLineDal;

public class AirLineBizImpl implements AirLineBiz {
	@Autowired
	private AirLineDal airLineDal;

	@Override
	public int save(AirLine airLine) {
		
		return airLineDal.save(airLine);
	}

	@Override
	public List<AirLine> findAirLineByCity(Date date, String depCity, String arrCity) throws IOException {
		
		return airLineDal.findAirLineByCity(date, depCity, arrCity);
	}

	@Override
	public List<AirLine> findAirLineByCity(String date, String depCity, String arrCity) throws IOException {
		return airLineDal.findAirLineByCity(date, depCity, arrCity);
	}

	@Override
	public List<AirLine> findAirLineByAirCode(String date, String airCode) throws IOException {
		
		return airLineDal.findAirLineByAirCode(date, airCode);
	}

	@Override
	public AirLine findAirLine(String date, String airCode, String depCity, String arrCity) throws IOException {
		return airLineDal.findAirLine(date, airCode, depCity, arrCity);
	}
	
	
}

package com.yimayhd.erpcenter.dal.basic.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.AirLine;


public interface AirLineDal {
	
	/**
	 * 保存航线
	 * @param airLine
	 * @return
	 */
	int save(AirLine airLine);
	
	/**
	 * @param date		起飞日期
	 * @param depCity	出发城市
	 * @param arrCity	到达城市
	 * @return
	 * @throws IOException 
	 */
	List<AirLine> findAirLineByCity(Date date, String depCity, String arrCity) throws IOException; 
	
	/**
	 * @param date		起飞日期（字符串格式2015-08-20）
	 * @param depCity	出发城市
	 * @param arrCity	到达城市
	 * @return
	 * @throws IOException 
	 */
	List<AirLine> findAirLineByCity(String date, String depCity, String arrCity) throws IOException; 
	
	/**
	 * 根据航班号查询航线
	 * @param date		出行日期
	 * @param airCode	航班号
	 * @return
	 * @throws IOException 
	 */
	List<AirLine> findAirLineByAirCode(String date,String airCode) throws IOException;
	
	/**
	 * 取得航班详细信息
	 */
	AirLine findAirLine(String date, String airCode, String depCity, String arrCity) throws IOException;
	
}

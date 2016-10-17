package com.yimayhd.erpcenter.biz.basic.client.air.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.biz.basic.air.po.AirLine;


public interface AirLineMapper {

	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int insert(AirLine record);
	/**
	 * 更新
	 * @param finalAirLine
	 */
	int update(AirLine finalAirLine);
	/**
	 * 根据出发日期、出发城市、到达城市条件查询航线
	 * @param date
	 * @param depCity
	 * @param arrCity
	 * @return
	 */
	List<AirLine> findAirLineByCity(@Param("depDate") String depDate,@Param("depCityCode") String depCityCode,@Param("arrCityCode") String arrCityCode);
	
	/**
	 * 根据不同条件查询航线结果
	 * @param condition
	 * @return
	 */
	List<AirLine> findAirLineByAirCode(@Param("depDate") String depDate,@Param("airCode") String airCode);
	
	/**
	 * 根据此四个字段筛选经停总航班
	 * @param date			起飞日期
	 * @param depCityCode	起飞城市三字码
	 * @param arrCityCode	降落城市三字码
	 * @param airCode		航班号
	 * @return
	 */
	AirLine getFinalAirLineByMultiConditon(@Param("depDate") String depDate, @Param("depCityCode") String depCityCode, @Param("arrCityCode") String arrCityCode,@Param("airCode") String airCode);
	
}
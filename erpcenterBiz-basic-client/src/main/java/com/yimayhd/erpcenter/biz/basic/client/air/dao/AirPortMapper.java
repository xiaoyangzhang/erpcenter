package com.yimayhd.erpcenter.biz.basic.client.air.dao;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.biz.basic.air.po.AirPort;


public interface AirPortMapper {
	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int insertSelective(AirPort record);
	/**
	 * 更新
	 * @param record
	 * @return
	 */
	int update(AirPort record);
	/**
	 * 根据业务主键返回记录
	 * @param key
	 * @return
	 */
	AirPort selectByPrimaryKey(Integer key);
	/**
	 * 根据城市名字返回城市三字码
	 * @param cityName
	 * @return
	 */
	String getCityCodeByCityName(String cityName);
	/**
	 * 根据机场名称返回业务对象
	 * @param string
	 * @return
	 */
	AirPort getAirPortByPortName(String string);
	
	/**
	 * 根据用户输入的城市名字前缀或拼音前缀查询机场列表（固定返回10条）
	 * @param cityName
	 * @return
	 */
	List<Map<String, String>> getFuzzySearchList(String cityName);
}
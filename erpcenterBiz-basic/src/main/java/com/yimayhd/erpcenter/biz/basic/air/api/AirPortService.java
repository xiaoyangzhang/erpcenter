package com.yimayhd.erpcenter.biz.basic.air.api;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.biz.basic.air.po.AirPort;


public interface AirPortService {
	
	/**
	 * 单条插入
	 * @param airPort 
	 * @return 影响的行数
	 */
	int save(AirPort airPort);
	
	/**
	 * 根据主键查询对象
	 * @param key
	 * @return
	 */
	AirPort selectByPrimaryKey(Integer key);
	
	/**
	 * 根据城市名字查询城市代码
	 * @param cityName   城市名字
	 * @return			   城市代码
	 */
	String getCityCodeByCityName(String cityName);
	
	/**
	 * 根据用户输入的城市名字前缀或拼音前缀查询机场列表（固定返回10条）
	 * @param cityName
	 * @return
	 */
	List<Map<String, String>> getFuzzySearchList(String cityName);
}

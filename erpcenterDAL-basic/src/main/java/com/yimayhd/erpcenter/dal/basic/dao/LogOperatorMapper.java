package com.yimayhd.erpcenter.dal.basic.dao;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.LogOperator;



public interface LogOperatorMapper{
	void insert(LogOperator info);
	void update(LogOperator info);
	void delete(String id);
	LogOperator getById(String id);
	List<LogOperator> getAll(LogOperator info);
	List<LogOperator> getWhere(LogOperator info);
	List<LogOperator> getWhere_NotLogText(LogOperator info);
	List<Map<String, Object>> getWhere_LogDetail(LogOperator info);
}

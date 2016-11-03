package com.yimayhd.erpcenter.biz.basic.service;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.LogOperator;




public interface LogOperatorBiz {
	void insert(List<LogOperator> list);
	void update(LogOperator info);
	void delete(String id);
	LogOperator getById(String id);
	List<LogOperator> getAll(LogOperator info);
	List<LogOperator> getWhere(LogOperator info);
	List<LogOperator> getWhere_NotLogText(LogOperator info);
	List<Map<String, Object>> getWhere_LogDetail(LogOperator info);
}

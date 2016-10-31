package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.LogOperatorBiz;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.basic.service.LogOperatorDal;

public class LogOperatorBizImpl implements LogOperatorBiz {
	@Autowired
	private LogOperatorDal logOperatorDal;
	
	@Override
	public void insert(List<LogOperator> list) {
		logOperatorDal.insert(list);
	}

	@Override
	public void update(LogOperator info) {
		logOperatorDal.update(info);
	}

	@Override
	public void delete(String id) {
		logOperatorDal.delete(id);
	}

	@Override
	public LogOperator getById(String id) {
		return logOperatorDal.getById(id);
	}

	@Override
	public List<LogOperator> getAll(LogOperator info) {
		return logOperatorDal.getAll(info);
	}

	@Override
	public List<LogOperator> getWhere(LogOperator info) {
		return logOperatorDal.getWhere(info);
	}

	@Override
	public List<LogOperator> getWhere_NotLogText(LogOperator info) {
		return logOperatorDal.getWhere_NotLogText(info);
	}
	
	@Override
	public List<Map<String, Object>> getWhere_LogDetail(LogOperator info) {
		return logOperatorDal.getWhere_LogDetail(info);
	}


}

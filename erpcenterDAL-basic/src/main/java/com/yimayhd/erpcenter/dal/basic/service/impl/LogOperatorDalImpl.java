package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.basic.dao.LogOperatorMapper;
import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.dal.basic.service.LogOperatorDal;

public class LogOperatorDalImpl implements LogOperatorDal {
	@Autowired
	private LogOperatorMapper logOperatorMapper;
	
	@Override
	public void insert(List<LogOperator> list) {
		long time = System.currentTimeMillis();
		for(LogOperator info : list){
			info.setBatchId(new Long(time).intValue());
			logOperatorMapper.insert(info);
		}
	}

	@Override
	public void update(LogOperator info) {
		logOperatorMapper.update(info);
	}

	@Override
	public void delete(String id) {
		logOperatorMapper.delete(id);
	}

	@Override
	public LogOperator getById(String id) {
		return logOperatorMapper.getById(id);
	}

	@Override
	public List<LogOperator> getAll(LogOperator info) {
		return logOperatorMapper.getAll(info);
	}

	@Override
	public List<LogOperator> getWhere(LogOperator info) {
		return logOperatorMapper.getWhere(info);
	}

	@Override
	public List<LogOperator> getWhere_NotLogText(LogOperator info) {
		return logOperatorMapper.getWhere_NotLogText(info);
	}
	
	@Override
	public List<Map<String, Object>> getWhere_LogDetail(LogOperator info) {
		return logOperatorMapper.getWhere_LogDetail(info);
	}


}

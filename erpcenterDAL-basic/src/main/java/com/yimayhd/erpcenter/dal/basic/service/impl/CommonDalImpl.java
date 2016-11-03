package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.service.CommonDal;

public class CommonDalImpl implements CommonDal {
	
	
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public PageBean queryListPage(String sqlId, PageBean pageBean) {
		
		pageBean.setResult(ss.selectList(sqlId, pageBean));
		return pageBean;
	}

	@Override
	public List queryList(String sqlId, Map param) {
		return ss.selectList(sqlId, param);
	}
	
	@Override
	public Map queryOne(String sqlId, Map param) {
		return ss.selectOne(sqlId, param);
	}
}

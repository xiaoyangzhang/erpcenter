package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.CommonBiz;
import com.yimayhd.erpcenter.dal.basic.service.CommonDal;

public class CommonBizImpl implements CommonBiz {
	
	@Autowired
	private CommonDal commonDal;

	@Override
	public PageBean queryListPage(String sqlId, PageBean pageBean) {
		PageBean result = commonDal.queryListPage(sqlId, pageBean);
		return result;
	}

	@Override
	public List queryList(String sqlId, Map param) {
		return commonDal.queryList(sqlId, param);
	}
	
	@Override
	public Map queryOne(String sqlId, Map param) {
		return commonDal.queryOne(sqlId, param);
	}
}

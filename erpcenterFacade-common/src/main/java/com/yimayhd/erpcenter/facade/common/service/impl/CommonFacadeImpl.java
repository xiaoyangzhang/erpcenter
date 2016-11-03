package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.List;
import java.util.Map;

import org.erpcenterFacade.common.client.service.CommonFacade;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.CommonBiz;

public class CommonFacadeImpl implements CommonFacade {

//	@Autowired
//	private SqlSessionTemplate ss;
	@Autowired
	private CommonBiz commonBiz;
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

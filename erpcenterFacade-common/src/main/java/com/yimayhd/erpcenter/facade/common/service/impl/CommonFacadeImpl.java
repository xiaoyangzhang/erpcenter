package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.List;
import java.util.Map;

import org.erpcenterFacade.common.client.service.CommonFacade;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.CommonSaleBiz;

public class CommonFacadeImpl implements CommonFacade {

//	@Autowired
//	private SqlSessionTemplate ss;
	@Autowired
	private CommonSaleBiz commonSaleBiz;
	@Override
	public PageBean queryListPage(String sqlId, PageBean pageBean) {
		
		PageBean result = commonSaleBiz.queryListPage(sqlId, pageBean);
		return result;
	}

	@Override
	public List queryList(String sqlId, Map param) {
		return commonSaleBiz.queryList(sqlId, param);
	}
	
	@Override
	public Map queryOne(String sqlId, Map param) {
		return commonSaleBiz.queryOne(sqlId, param);
	}
	
}

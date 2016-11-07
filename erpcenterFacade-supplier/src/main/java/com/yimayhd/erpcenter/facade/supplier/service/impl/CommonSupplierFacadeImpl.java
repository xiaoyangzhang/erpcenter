package com.yimayhd.erpcenter.facade.supplier.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.supplier.service.CommonSupplierFacade;
import com.yimayhd.erpresource.biz.service.CommonSupplierBiz;

public class CommonSupplierFacadeImpl implements CommonSupplierFacade {
	@Autowired
	private CommonSupplierBiz commonSupplierBiz;
	@Override
	public PageBean queryListPage(String sqlId, PageBean pageBean) {
		return commonSupplierBiz.queryListPage(sqlId, pageBean);
	}

	@Override
	public List queryList(String sqlId, Map param) {
		return commonSupplierBiz.queryList(sqlId, param);
	}

	@Override
	public Map queryOne(String sqlId, Map param) {
		return commonSupplierBiz.queryOne(sqlId, param);
	}

}

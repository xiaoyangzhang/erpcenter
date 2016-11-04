package com.yimayhd.erpcenter.dal.sales;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.CommonDal;

public class CommonDalImpl implements CommonDal {
	
	
	@Autowired
	private SqlSessionTemplate sqlSessionSales;

	@Override
	public PageBean queryListPage(String sqlId, PageBean pageBean) {
		
		pageBean.setResult(sqlSessionSales.selectList(sqlId, pageBean));
		return pageBean;
	}

	@Override
	public List queryList(String sqlId, Map param) {
		return sqlSessionSales.selectList(sqlId, param);
	}
	
	@Override
	public Map queryOne(String sqlId, Map param) {
		return sqlSessionSales.selectOne(sqlId, param);
	}
}

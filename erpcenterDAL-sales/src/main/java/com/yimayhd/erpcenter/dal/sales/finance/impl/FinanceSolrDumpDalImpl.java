package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.solr.SubjectSummary;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceSolrDumpDal;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceSolrDumpMapper;


public class FinanceSolrDumpDalImpl implements FinanceSolrDumpDal{
	
	@Autowired
	private FinanceSolrDumpMapper solrDumpFinanceMapper;

	@Override
	public PageBean<SubjectSummary> selectSubjectSummary1DumpListPage(PageBean<SubjectSummary> pageBean) {
		List<SubjectSummary> result = solrDumpFinanceMapper.selectSubjectSummary1DumpListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public PageBean<SubjectSummary> selectSubjectSummary2DumpListPage(PageBean<SubjectSummary> pageBean) {
		List<SubjectSummary> result = solrDumpFinanceMapper.selectSubjectSummary2DumpListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}

	
}

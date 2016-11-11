package com.yimayhd.erpcenter.dal.sales.client.finance.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.solr.SubjectSummary;



/**
 * 财务管理
 * @author hongfei.guo
 */
public interface FinanceSolrDumpDal{
	
	/**
	 * 科目汇总统计1、3
	 * @param pageBean
	 * @return
	 */
	PageBean<SubjectSummary> selectSubjectSummary1DumpListPage(PageBean<SubjectSummary> pageBean);
	
	/**
	 * 科目汇总统计2
	 * @param pageBean
	 * @return
	 */
	PageBean<SubjectSummary> selectSubjectSummary2DumpListPage(PageBean<SubjectSummary> pageBean);
}

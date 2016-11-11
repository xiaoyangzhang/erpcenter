package com.yimayhd.erpcenter.dal.sales.finance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.solr.SubjectSummary;

public interface FinanceSolrDumpMapper {
	
	
	/**
	 * 科目汇总统计1、3
	 * @return
	 */
	List<SubjectSummary> selectSubjectSummary1DumpListPage(@Param("page") PageBean page);
	
	/**
	 * 科目汇总统计2
	 * @return
	 */
	List<SubjectSummary> selectSubjectSummary2DumpListPage(@Param("page") PageBean page);
	
}
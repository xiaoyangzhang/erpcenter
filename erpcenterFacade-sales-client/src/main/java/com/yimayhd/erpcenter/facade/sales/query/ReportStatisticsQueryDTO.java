package com.yimayhd.erpcenter.facade.sales.query;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

public class ReportStatisticsQueryDTO extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7036051477214636033L;

	private  PageBean<GroupOrder> pageBean;
	
	private Integer bizId;

	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	
}

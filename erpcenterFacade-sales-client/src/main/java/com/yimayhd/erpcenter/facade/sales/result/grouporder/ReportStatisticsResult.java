package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ReportStatisticsResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3170238633932857375L;
	
	
	private PageBean<GroupOrder> pageBean;


	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}


	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}
	
	

}

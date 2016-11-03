package com.yimayhd.erpcenter.facade.tj.client.result;

import java.math.BigDecimal;
import java.util.HashMap;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class AdminOrderListTableResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, BigDecimal> sumResAdminOrder;
	private PageBean<GroupOrder> pageBean;
	public HashMap<String, BigDecimal> getSumResAdminOrder() {
		return sumResAdminOrder;
	}
	public void setSumResAdminOrder(HashMap<String, BigDecimal> sumResAdminOrder) {
		this.sumResAdminOrder = sumResAdminOrder;
	}
	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}
	
}

package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GetFitOrderListDataResult extends BaseStateResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2590926117763542914L;
	private PageBean<GroupOrder> pageBean;

	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}
}

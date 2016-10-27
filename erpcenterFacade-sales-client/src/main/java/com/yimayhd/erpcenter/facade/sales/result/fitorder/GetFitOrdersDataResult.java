package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GetFitOrdersDataResult extends BaseStateResult {
	private static final long serialVersionUID = 4279363440995977362L;
	private PageBean pageBean;
	private GroupOrder order;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public GroupOrder getOrder() {
		return order;
	}

	public void setOrder(GroupOrder order) {
		this.order = order;
	}
}

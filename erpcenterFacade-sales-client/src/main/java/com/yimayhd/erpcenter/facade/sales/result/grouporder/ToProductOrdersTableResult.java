package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToProductOrdersTableResult extends BaseStateResult {
	private static final long serialVersionUID = 3168176933465143630L;
	private PageBean<GroupOrder> pageBean;
	private GroupOrder go;

	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}

	public GroupOrder getGo() {
		return go;
	}

	public void setGo(GroupOrder go) {
		this.go = go;
	}
}

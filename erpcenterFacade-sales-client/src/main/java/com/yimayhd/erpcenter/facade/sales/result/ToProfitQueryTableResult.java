package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToProfitQueryTableResult {
	private PageBean<GroupOrder> pageBean;
	private GroupOrder staticInfo;
	private GroupOrder groupOrder;

	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}

	public GroupOrder getStaticInfo() {
		return staticInfo;
	}

	public void setStaticInfo(GroupOrder staticInfo) {
		this.staticInfo = staticInfo;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
}

package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToProfitQueryTableResult implements Serializable {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
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

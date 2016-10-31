package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToImpNotGroupListResult extends BaseStateResult {
	private static final long serialVersionUID = -61522686279328518L;
	private GroupOrder groupOrder;
	private PageBean pageBean;
	private List<DicInfo> pp;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<DicInfo> getPp() {
		return pp;
	}

	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}
}

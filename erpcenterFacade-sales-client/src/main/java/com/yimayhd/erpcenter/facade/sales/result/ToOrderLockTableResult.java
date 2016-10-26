package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class ToOrderLockTableResult implements Serializable {

	private String totalPb;
	private PageBean<GroupOrder> pageBean;

	public String getTotalPb() {
		return totalPb;
	}

	public void setTotalPb(String totalPb) {
		this.totalPb = totalPb;
	}

	public PageBean<GroupOrder> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<GroupOrder> pageBean) {
		this.pageBean = pageBean;
	}

}

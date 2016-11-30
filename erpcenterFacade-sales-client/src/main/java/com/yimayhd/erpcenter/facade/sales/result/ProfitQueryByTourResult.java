package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

//按团查询利润
public class ProfitQueryByTourResult implements Serializable{
	private PageBean<TourGroup> pageBean;
	private TourGroup group;
	private PageBean<TourGroup> pb;


	private TourGroup groupCost;
	public TourGroup getGroupCost() {
		return groupCost;
	}

	public void setGroupCost(TourGroup groupCost) {
		this.groupCost = groupCost;
	}


	public PageBean<TourGroup> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<TourGroup> pageBean) {
		this.pageBean = pageBean;
	}

	public TourGroup getGroup() {
		return group;
	}

	public void setGroup(TourGroup group) {
		this.group = group;
	}

	public PageBean<TourGroup> getPb() {
		return pb;
	}

	public void setPb(PageBean<TourGroup> pb) {
		this.pb = pb;
	}
}

package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class FitTotalSKGroupQueryResult implements Serializable {
		
	private static final long serialVersionUID = 206259454845784428L;
	
	private TourGroup group;
	private PageBean pageBean;
	private TourGroup tourGroup;

	public TourGroup getGroup() {
		return group;
	}

	public void setGroup(TourGroup group) {
		this.group = group;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
}

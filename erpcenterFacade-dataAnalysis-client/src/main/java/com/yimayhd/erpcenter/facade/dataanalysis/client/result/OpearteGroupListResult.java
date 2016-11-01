package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class OpearteGroupListResult extends BaseResult {
	private static final long serialVersionUID = -8328683076516918116L;
	private PageBean pageBean;
	private TourGroupVO tourGroup;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public TourGroupVO getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroupVO tourGroup) {
		this.tourGroup = tourGroup;
	}
}

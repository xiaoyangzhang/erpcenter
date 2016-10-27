package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GetInsertFitGroupListResult extends BaseStateResult {
	private static final long serialVersionUID = 8942704005753376837L;
	private PageBean pageBean;
	private TourGroup tourGroup;

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

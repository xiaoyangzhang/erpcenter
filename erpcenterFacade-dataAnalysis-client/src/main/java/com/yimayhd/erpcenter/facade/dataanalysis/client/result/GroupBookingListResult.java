package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class GroupBookingListResult extends BaseResult {
	private static final long serialVersionUID = 4415299658171818713L;
	private PageBean pageBean;
	private Map<String, Object> sum;
	private TourGroupVO tourGroup;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Map<String, Object> getSum() {
		return sum;
	}

	public void setSum(Map<String, Object> sum) {
		this.sum = sum;
	}

	public TourGroupVO getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroupVO tourGroup) {
		this.tourGroup = tourGroup;
	}
}

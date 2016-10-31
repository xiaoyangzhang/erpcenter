package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToFitOrderListResult extends BaseStateResult {
	private static final long serialVersionUID = 5389392715679734005L;
	private List<GroupOrder> list;
	private TourGroup tourGroup;

	public List<GroupOrder> getList() {
		return list;
	}

	public void setList(List<GroupOrder> list) {
		this.list = list;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
}

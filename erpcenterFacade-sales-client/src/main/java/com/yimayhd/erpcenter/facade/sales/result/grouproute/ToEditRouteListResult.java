package com.yimayhd.erpcenter.facade.sales.result.grouproute;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToEditRouteListResult extends BaseStateResult {
	private static final long serialVersionUID = -1365921400586575450L;
	private TourGroup tourGroup;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
}

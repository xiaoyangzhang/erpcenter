package com.yimayhd.erpcenter.facade.sales.result.grouproute;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToGroupRouteResult extends BaseStateResult {
	private static final long serialVersionUID = -4843916549621875873L;
	private TourGroup tourGroup;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
}

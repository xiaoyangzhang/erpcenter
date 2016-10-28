package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class EditOrderGroupInfoDTO implements Serializable {
	private static final long serialVersionUID = -4945281065403257705L;
	private TourGroup tourGroup;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
}

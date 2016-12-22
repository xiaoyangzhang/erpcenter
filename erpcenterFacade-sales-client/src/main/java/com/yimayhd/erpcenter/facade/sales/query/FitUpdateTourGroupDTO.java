package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.io.Serializable;

public class FitUpdateTourGroupDTO implements Serializable{
	
	private TourGroup tourGroup;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
}

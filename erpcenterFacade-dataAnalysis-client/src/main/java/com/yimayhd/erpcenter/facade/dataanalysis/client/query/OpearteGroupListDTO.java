package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class OpearteGroupListDTO extends BaseDTO {
	private static final long serialVersionUID = 8223238636434021205L;
	private TourGroupVO tourGroup;

	public TourGroupVO getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroupVO tourGroup) {
		this.tourGroup = tourGroup;
	}
}

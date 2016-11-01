package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToFitEditResult extends BaseStateResult {
	private static final long serialVersionUID = -6655996390994224836L;
	private TourGroup tourGroup;
	private List<DicInfo> ppList;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

	public List<DicInfo> getPpList() {
		return ppList;
	}

	public void setPpList(List<DicInfo> ppList) {
		this.ppList = ppList;
	}
}

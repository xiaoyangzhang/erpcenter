package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

public class TranportListResult extends BaseResult {
	private static final long serialVersionUID = 4352122520887551663L;
	private List<DicInfo> transportTypeList;

	public List<DicInfo> getTransportTypeList() {
		return transportTypeList;
	}

	public void setTransportTypeList(List<DicInfo> transportTypeList) {
		this.transportTypeList = transportTypeList;
	}
}

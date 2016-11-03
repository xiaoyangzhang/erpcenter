package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

public class AllProvinceResult extends BaseResult {
	private static final long serialVersionUID = -1794927468755402200L;
	private List<RegionInfo> allProvince;

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}
}

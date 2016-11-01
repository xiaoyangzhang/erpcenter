package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

public class RestaurantQueriesResult extends BaseResult {
	private static final long serialVersionUID = -6765487479335106503L;
	private List<DicInfo> type1;
	private List<RegionInfo> allProvince;
	private List<DicInfo> cashTypes;
	private List<DicInfo> levelList;

	public List<DicInfo> getType1() {
		return type1;
	}

	public void setType1(List<DicInfo> type1) {
		this.type1 = type1;
	}

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public List<DicInfo> getCashTypes() {
		return cashTypes;
	}

	public void setCashTypes(List<DicInfo> cashTypes) {
		this.cashTypes = cashTypes;
	}

	public List<DicInfo> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<DicInfo> levelList) {
		this.levelList = levelList;
	}
}

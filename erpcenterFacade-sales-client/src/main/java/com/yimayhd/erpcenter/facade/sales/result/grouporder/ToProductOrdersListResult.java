package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToProductOrdersListResult extends BaseStateResult {
	private static final long serialVersionUID = 4680877637453715153L;
	private List<RegionInfo> allProvince;
	private String orgJsonStr;
	private String orgUserJsonStr;

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public String getOrgJsonStr() {
		return orgJsonStr;
	}

	public void setOrgJsonStr(String orgJsonStr) {
		this.orgJsonStr = orgJsonStr;
	}

	public String getOrgUserJsonStr() {
		return orgUserJsonStr;
	}

	public void setOrgUserJsonStr(String orgUserJsonStr) {
		this.orgUserJsonStr = orgUserJsonStr;
	}
}

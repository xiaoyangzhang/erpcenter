package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToDeliveryPriceListResult extends BaseStateResult {
	private static final long serialVersionUID = -6280251133175908526L;
	private String orgJsonStr;
	private String orgUserJsonStr;
	private List<DicInfo> lysfxmList;

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

	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}

	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}
}

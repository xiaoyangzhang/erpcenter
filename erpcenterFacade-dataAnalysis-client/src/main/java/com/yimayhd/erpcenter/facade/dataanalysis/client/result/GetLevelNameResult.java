package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

public class GetLevelNameResult extends BaseResult {
	private static final long serialVersionUID = 1567440263091480474L;
	private List<DicInfo> jdxjList;

	public List<DicInfo> getJdxjList() {
		return jdxjList;
	}

	public void setJdxjList(List<DicInfo> jdxjList) {
		this.jdxjList = jdxjList;
	}
}

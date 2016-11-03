package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

public class ToProductListResult extends BaseResult {
	private static final long serialVersionUID = -6395073900077558694L;
	private List<DicInfo> pp;

	public List<DicInfo> getPp() {
		return pp;
	}

	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}
}

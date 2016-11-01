package com.yimayhd.erpcenter.facade.sales.result.quality;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class QualityListResult extends BaseStateResult {
	private static final long serialVersionUID = 8053385863330166516L;
	private List<DicInfo> brandList;

	public List<DicInfo> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<DicInfo> brandList) {
		this.brandList = brandList;
	}
}

package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class CreateShoppingDetailResult extends BaseStateResult {
	private static final long serialVersionUID = -6111089658141423946L;
	private TourGroup tg;
	private List<GroupOrderPrintPo> gops;
	private String guideString;

	public TourGroup getTg() {
		return tg;
	}

	public void setTg(TourGroup tg) {
		this.tg = tg;
	}

	public List<GroupOrderPrintPo> getGops() {
		return gops;
	}

	public void setGops(List<GroupOrderPrintPo> gops) {
		this.gops = gops;
	}

	public String getGuideString() {
		return guideString;
	}

	public void setGuideString(String guideString) {
		this.guideString = guideString;
	}
}

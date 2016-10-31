package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToShoppingDetailPreviewResult extends BaseStateResult {
	private static final long serialVersionUID = -7006092824760606994L;
	private List<GroupOrderPrintPo> gops;
	private TourGroup tg;
	private String guideString;

	public List<GroupOrderPrintPo> getGops() {
		return gops;
	}

	public void setGops(List<GroupOrderPrintPo> gops) {
		this.gops = gops;
	}

	public TourGroup getTg() {
		return tg;
	}

	public void setTg(TourGroup tg) {
		this.tg = tg;
	}

	public String getGuideString() {
		return guideString;
	}

	public void setGuideString(String guideString) {
		this.guideString = guideString;
	}
}

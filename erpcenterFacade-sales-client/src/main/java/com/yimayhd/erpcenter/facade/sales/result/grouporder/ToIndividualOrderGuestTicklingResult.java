package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToIndividualOrderGuestTicklingResult extends BaseStateResult{
	private static final long serialVersionUID = -3105761920161813587L;
	private TourGroup tg;
	private String guideString;
	private GroupOrderPrintPo gop;
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
	public GroupOrderPrintPo getGop() {
		return gop;
	}
	public void setGop(GroupOrderPrintPo gop) {
		this.gop = gop;
	}
}

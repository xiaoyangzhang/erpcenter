package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToIndividualGuestTicklingResult extends BaseStateResult {
	private static final long serialVersionUID = -1177681929701574993L;
	private TourGroup tg;
	private List<Integer> list;
	private List<GroupOrderPrintPo> gops;
	private String guideString;

	public TourGroup getTg() {
		return tg;
	}

	public void setTg(TourGroup tg) {
		this.tg = tg;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
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

package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class CreatetOrderTicklingResult extends BaseStateResult {
	private static final long serialVersionUID = -2007096725850201202L;
	private TourGroup tg;
	private String guideString;
	private GroupOrderPrintPo gop;
	private Integer numAdult;
	private Integer numChild;

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

	public Integer getNumAdult() {
		return numAdult;
	}

	public void setNumAdult(Integer numAdult) {
		this.numAdult = numAdult;
	}

	public Integer getNumChild() {
		return numChild;
	}

	public void setNumChild(Integer numChild) {
		this.numChild = numChild;
	}
}

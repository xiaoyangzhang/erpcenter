package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class PreviewGuestWithoutTransResult extends BaseStateResult {
	private static final long serialVersionUID = -7173534023439133032L;
	private TourGroup tg;
	private List<GroupOrderGuest> guestAllList;

	public TourGroup getTg() {
		return tg;
	}

	public void setTg(TourGroup tg) {
		this.tg = tg;
	}

	public List<GroupOrderGuest> getGuestAllList() {
		return guestAllList;
	}

	public void setGuestAllList(List<GroupOrderGuest> guestAllList) {
		this.guestAllList = guestAllList;
	}
}

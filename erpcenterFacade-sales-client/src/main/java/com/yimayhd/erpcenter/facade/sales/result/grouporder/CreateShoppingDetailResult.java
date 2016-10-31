package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class CreateShoppingDetailResult extends BaseStateResult {
	private static final long serialVersionUID = -605812753076086951L;
	private List<Map<String, String>> guestList;
	private Map<String, Object> params1;
	private Map<String, Object> map0;

	public List<Map<String, String>> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<Map<String, String>> guestList) {
		this.guestList = guestList;
	}

	public Map<String, Object> getParams1() {
		return params1;
	}

	public void setParams1(Map<String, Object> params1) {
		this.params1 = params1;
	}

	public Map<String, Object> getMap0() {
		return map0;
	}

	public void setMap0(Map<String, Object> map0) {
		this.map0 = map0;
	}
}

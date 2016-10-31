package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class CreateSKGuideResult extends BaseStateResult {
	private static final long serialVersionUID = -8747672818248263123L;
	private Map<String, Object> params1;
	private Map<String, Object> map1;
	private List<Map<String, String>> routeList;
	private List<Map<String, String>> hotelList;
	private List<Map<String, String>> orderList;
	private List<Map<String, String>> mapList;
	private Map<String, Object> map2;

	public Map<String, Object> getParams1() {
		return params1;
	}

	public void setParams1(Map<String, Object> params1) {
		this.params1 = params1;
	}

	public Map<String, Object> getMap1() {
		return map1;
	}

	public void setMap1(Map<String, Object> map1) {
		this.map1 = map1;
	}

	public List<Map<String, String>> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Map<String, String>> routeList) {
		this.routeList = routeList;
	}

	public List<Map<String, String>> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Map<String, String>> hotelList) {
		this.hotelList = hotelList;
	}

	public List<Map<String, String>> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Map<String, String>> orderList) {
		this.orderList = orderList;
	}

	public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}

	public Map<String, Object> getMap2() {
		return map2;
	}

	public void setMap2(Map<String, Object> map2) {
		this.map2 = map2;
	}
}

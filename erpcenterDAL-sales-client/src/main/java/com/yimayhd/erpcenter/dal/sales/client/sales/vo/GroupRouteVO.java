package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class GroupRouteVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<GroupRouteDayVO> groupRouteDayVOList;
	TourGroup tourGroup;
	Integer orderId;
	List<GroupOrder> orderList;

	public List<GroupRouteDayVO> getGroupRouteDayVOList() {
		return groupRouteDayVOList;
	}

	public void setGroupRouteDayVOList(List<GroupRouteDayVO> groupRouteDayVOList) {
		this.groupRouteDayVOList = groupRouteDayVOList;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<GroupOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<GroupOrder> orderList) {
		this.orderList = orderList;
	}

}

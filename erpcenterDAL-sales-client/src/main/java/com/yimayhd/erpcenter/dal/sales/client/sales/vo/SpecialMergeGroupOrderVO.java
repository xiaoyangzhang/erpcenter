package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihg.sales.po.GroupOrder;
import com.yihg.sales.po.TourGroup;

public class SpecialMergeGroupOrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GroupOrder> orderList=new ArrayList<GroupOrder>();
	private GroupRouteVO groupRouteVO;
	private TourGroup tourGroup;

	public SpecialMergeGroupOrderVO() {
	}

	public List<GroupOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<GroupOrder> orderList) {
		this.orderList = orderList;
	}

	public GroupRouteVO getGroupRouteVO() {
		return groupRouteVO;
	}

	public void setGroupRouteVO(GroupRouteVO groupRouteVO) {
		this.groupRouteVO = groupRouteVO;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

}

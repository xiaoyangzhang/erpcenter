package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.GroupOrder;
import com.yihg.sales.po.TourGroup;

public class FitGroupInfoVO implements Serializable {
	private TourGroup tourGroup;
	private List<GroupOrder> groupOrderList ;
	private List<GroupRouteDayVO> groupRouteDayVOList;
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<GroupOrder> getGroupOrderList() {
		return groupOrderList;
	}
	public void setGroupOrderList(List<GroupOrder> groupOrderList) {
		this.groupOrderList = groupOrderList;
	}
	public List<GroupRouteDayVO> getGroupRouteDayVOList() {
		return groupRouteDayVOList;
	}
	public void setGroupRouteDayVOList(List<GroupRouteDayVO> groupRouteDayVOList) {
		this.groupRouteDayVOList = groupRouteDayVOList;
	}
	
	
}

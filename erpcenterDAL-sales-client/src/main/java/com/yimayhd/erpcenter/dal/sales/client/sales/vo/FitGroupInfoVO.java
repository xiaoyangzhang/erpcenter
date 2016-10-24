package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class FitGroupInfoVO implements Serializable {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -461489398900323383L;
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

package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class MergeGroupOrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GroupOrder> orderList=new ArrayList<GroupOrder>();
	private GroupRouteVO groupRouteVO;
	private TourGroup tourGroup;
	private String productCode;
	private Integer choseOperator;
	private String choseOperatorName;
	

	public MergeGroupOrderVO() {
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getChoseOperator() {
		return choseOperator;
	}

	public void setChoseOperator(Integer choseOperator) {
		this.choseOperator = choseOperator;
	}

	public String getChoseOperatorName() {
		return choseOperatorName;
	}

	public void setChoseOperatorName(String choseOperatorName) {
		this.choseOperatorName = choseOperatorName;
	}
	

}

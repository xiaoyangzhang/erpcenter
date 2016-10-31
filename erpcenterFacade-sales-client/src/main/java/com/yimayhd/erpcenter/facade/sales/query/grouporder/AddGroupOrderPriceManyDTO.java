package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupOrderPriceVO;

public class AddGroupOrderPriceManyDTO implements Serializable {
	private static final long serialVersionUID = 7403495104366070598L;
	private GroupOrderPriceVO groupOrderPriceVO;
	private Integer userId;
	private String userName;

	public GroupOrderPriceVO getGroupOrderPriceVO() {
		return groupOrderPriceVO;
	}

	public void setGroupOrderPriceVO(GroupOrderPriceVO groupOrderPriceVO) {
		this.groupOrderPriceVO = groupOrderPriceVO;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

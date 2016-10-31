package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupOrderVO;

public class AddGroupOrderDTO implements Serializable {
	private static final long serialVersionUID = 1053348986016010649L;
	private GroupOrderVO groupOrderVO;
	private Integer priceId;
	private Integer priceGroupId;
	
	private Integer userId;
	private String userName;

	public GroupOrderVO getGroupOrderVO() {
		return groupOrderVO;
	}

	public void setGroupOrderVO(GroupOrderVO groupOrderVO) {
		this.groupOrderVO = groupOrderVO;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getPriceGroupId() {
		return priceGroupId;
	}

	public void setPriceGroupId(Integer priceGroupId) {
		this.priceGroupId = priceGroupId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

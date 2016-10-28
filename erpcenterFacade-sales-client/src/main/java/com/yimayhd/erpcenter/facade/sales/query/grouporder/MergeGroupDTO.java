package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;

public class MergeGroupDTO implements Serializable {
	private static final long serialVersionUID = 5635740463604071788L;
	private MergeGroupOrderVO mergeGroupOrderVO;
	private Integer bizId;
	private Integer userId;
	private String userName;
	private String bizCode;

	public MergeGroupOrderVO getMergeGroupOrderVO() {
		return mergeGroupOrderVO;
	}

	public void setMergeGroupOrderVO(MergeGroupOrderVO mergeGroupOrderVO) {
		this.mergeGroupOrderVO = mergeGroupOrderVO;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
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

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
}

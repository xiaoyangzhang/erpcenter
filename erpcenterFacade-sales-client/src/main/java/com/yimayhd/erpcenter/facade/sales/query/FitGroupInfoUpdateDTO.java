package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;

public class FitGroupInfoUpdateDTO implements Serializable {

	private Integer userId;
	private String userName;
	FitGroupInfoVO fitGroupInfoVO;

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

	public FitGroupInfoVO getFitGroupInfoVO() {
		return fitGroupInfoVO;
	}

	public void setFitGroupInfoVO(FitGroupInfoVO fitGroupInfoVO) {
		this.fitGroupInfoVO = fitGroupInfoVO;
	}
}

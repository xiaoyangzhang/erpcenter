package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;

public class SaveFitOrderInfoDTO implements Serializable{
	private static final long serialVersionUID = -5743066270415567332L;
	private FitOrderVO fitOrderVO;
	private Integer curUserId;
	private Integer curBizId;
	private String curUserName;
	private String bizCode;

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public FitOrderVO getFitOrderVO() {
		return fitOrderVO;
	}

	public void setFitOrderVO(FitOrderVO fitOrderVO) {
		this.fitOrderVO = fitOrderVO;
	}

	public Integer getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Integer curUserId) {
		this.curUserId = curUserId;
	}

	public Integer getCurBizId() {
		return curBizId;
	}

	public void setCurBizId(Integer curBizId) {
		this.curBizId = curBizId;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}
}

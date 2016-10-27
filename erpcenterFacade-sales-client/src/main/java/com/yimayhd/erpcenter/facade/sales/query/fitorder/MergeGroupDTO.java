package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;

public class MergeGroupDTO implements Serializable {
	private static final long serialVersionUID = -8616446620636585107L;
	private MergeGroupOrderVO mergeGroupOrderVO;
	private Integer bizId;
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

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
}

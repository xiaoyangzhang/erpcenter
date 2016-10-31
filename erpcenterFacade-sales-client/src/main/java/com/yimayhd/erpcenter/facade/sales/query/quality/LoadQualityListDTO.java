package com.yimayhd.erpcenter.facade.sales.query.quality;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupCondition;

public class LoadQualityListDTO implements Serializable {
	private static final long serialVersionUID = 2843654132972301460L;
	private QualityTourGroupCondition condition;
	private Integer curBizId;

	public QualityTourGroupCondition getCondition() {
		return condition;
	}

	public void setCondition(QualityTourGroupCondition condition) {
		this.condition = condition;
	}

	public Integer getCurBizId() {
		return curBizId;
	}

	public void setCurBizId(Integer curBizId) {
		this.curBizId = curBizId;
	}
}

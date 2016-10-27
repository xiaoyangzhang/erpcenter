package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;

public class DelGroupOrderDTO implements Serializable {
	private static final long serialVersionUID = -8357012369777997588L;
	private Integer bizId;
	private Integer id;

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

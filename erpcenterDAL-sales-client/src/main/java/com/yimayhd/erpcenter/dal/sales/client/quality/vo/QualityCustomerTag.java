package com.yimayhd.erpcenter.dal.sales.client.quality.vo;

import java.io.Serializable;

public class QualityCustomerTag implements Serializable {
	private String tagName;
	private Integer tagNameCnt;
	private Integer type;
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getTagNameCnt() {
		return tagNameCnt;
	}
	public void setTagNameCnt(Integer tagNameCnt) {
		this.tagNameCnt = tagNameCnt;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}

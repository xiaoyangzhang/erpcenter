package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class TourGroupComment implements Serializable {

	/**
	 * 组团社版本使用的 出团说明 和 注意事项。
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer bizId;
	private Integer groupId;
	private String groupComment; //出团说明
	private String groupNotice; //注意事项
	public String getGroupComment() {
		return groupComment;
	}
	public void setGroupComment(String groupComment) {
		this.groupComment = groupComment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupNotice() {
		return groupNotice;
	}
	public void setGroupNotice(String groupNotice) {
		this.groupNotice = groupNotice;
	}
}
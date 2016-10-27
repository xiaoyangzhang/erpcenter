package com.yimayhd.erpcenter.dal.sales.client.message;

import java.io.Serializable;

/**
 * 
 * 团信息变更消息对象
 *
 */
public class TourGroupUpdatedMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6563635974733836283L;

	/**
	 * 团id
	 */
	private Integer groupId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}

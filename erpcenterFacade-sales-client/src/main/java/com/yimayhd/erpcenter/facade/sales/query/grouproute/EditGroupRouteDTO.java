package com.yimayhd.erpcenter.facade.sales.query.grouproute;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;

public class EditGroupRouteDTO implements Serializable {
	private static final long serialVersionUID = 2407674821268076318L;
	private GroupRouteVO groupRouteVO;

	public GroupRouteVO getGroupRouteVO() {
		return groupRouteVO;
	}

	public void setGroupRouteVO(GroupRouteVO groupRouteVO) {
		this.groupRouteVO = groupRouteVO;
	}
}

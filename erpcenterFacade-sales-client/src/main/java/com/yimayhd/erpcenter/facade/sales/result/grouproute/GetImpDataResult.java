package com.yimayhd.erpcenter.facade.sales.result.grouproute;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GetImpDataResult extends BaseStateResult {
	private static final long serialVersionUID = -4676511191789741348L;
	private GroupRouteVO groupRouteVO;

	public GroupRouteVO getGroupRouteVO() {
		return groupRouteVO;
	}

	public void setGroupRouteVO(GroupRouteVO groupRouteVO) {
		this.groupRouteVO = groupRouteVO;
	}
}

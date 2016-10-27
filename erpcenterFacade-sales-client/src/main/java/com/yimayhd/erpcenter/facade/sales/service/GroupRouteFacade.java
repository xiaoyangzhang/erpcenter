package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.grouproute.EditGroupRouteDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.GetImpDataResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.ToEditRouteListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.ToGroupRouteResult;
import com.yimayhd.erpcenter.facade.sales.result.grouproute.ToImpRouteListResult;

/**
 * 
 * @author gaotingping
 * 2016年10月27日
 */
public interface GroupRouteFacade {

	ToEditRouteListResult toEditRouteList(Integer groupId);

	ToImpRouteListResult toImpRouteList(Integer productId, Integer orderId,Integer groupId);

	GetImpDataResult getImpData(Integer productId);

	GetImpDataResult getDataByGroupId(Integer groupId);

	GetImpDataResult getDataByOrderId(Integer orderId);

	BaseStateResult editGroupRoute(EditGroupRouteDTO editGroupRouteDTO);

	BaseStateResult saveGroupRoute(EditGroupRouteDTO editGroupRouteDTO);

	ToGroupRouteResult toGroupRoute(Integer groupId);

}

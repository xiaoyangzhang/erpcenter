package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.sales.po.GroupRoute;
import com.yihg.sales.po.GroupRouteAttachment;
import com.yihg.sales.po.GroupRouteSupplier;
import com.yihg.sales.po.GroupRouteTraffic;
import com.yihg.sales.vo.GroupRouteVO;

public interface GroupRouteService {

	List<GroupRoute> selectByGroupId(Integer groupId);
	List<GroupRoute> selectByGroupIdAndBookingId(Integer groupId,Integer bookingId);

	List<GroupRoute> selectByOrderId(Integer orderId);
	
	/**
     * 根据订单id查询当前订单下天数和结束日期
     * @param orderId
     * @return
     */
	GroupRoute selectDayNumAndMaxday(Integer orderId);

	List<GroupRouteSupplier> selectGroupRouteSupplierByRouteId(
			Integer groupRouteId);

	List<GroupRouteSupplier> selectGroupRouteSupplierByRouteIdAndType(
			Integer groupRouteId,Integer type);


	List<GroupRouteTraffic> selectGroupRouteTrafficByRouteId(
			Integer groupRouteId);

	void saveGroupRoute(GroupRouteVO groupRouteVO);

	void editGroupRoute(GroupRouteVO groupRouteVO);

	GroupRouteVO findGroupRouteByGroupId(Integer groupId);
	
	GroupRouteVO findGroupRouteByGroupIdAndSupplierId(Integer groupId,Integer supplierId);

	GroupRouteVO findGroupRouteByOrderId(Integer orderId);

	List<GroupRouteSupplier> selectSupplierByGroupIdAndType(Integer groupId, Integer type);
	
	
	//by Ouzy 2016.8.24
	List<GroupRouteSupplier> selectGroupRouteSupplierByGroupId(Integer groupId);
	List<GroupRouteSupplier> selectGroupRouteSupplierByOrderId(Integer orderId);
    List<GroupRouteAttachment> selectAttachmentByGroupId(Integer groupId);
    List<GroupRouteAttachment> selectAttachmentByOrderId(Integer orderId);
}

package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRouteDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;

public class GroupRouteBizImpl implements GroupRouteBiz {

	@Autowired
	private GroupRouteDal groupRouteDal;

	@Override
	public List<GroupRoute> selectByGroupId(Integer groupId) {
		return groupRouteDal.selectByGroupId(groupId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByRouteId(
			Integer groupRouteId) {
		return groupRouteDal
				.selectGroupRouteSupplierByRouteId(groupRouteId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByRouteIdAndType(
			Integer groupRouteId,Integer type) {
		return groupRouteDal
				.selectGroupRouteSupplierByRouteIdAndType(groupRouteId,type);
	}


	@Override
	public List<GroupRouteTraffic> selectGroupRouteTrafficByRouteId(
			Integer groupRouteId) {
		return groupRouteDal
				.selectGroupRouteTrafficByRouteId(groupRouteId);
	}

	@Override
	public void saveGroupRoute(GroupRouteVO groupRouteVO) {
		groupRouteDal.saveGroupRoute(groupRouteVO);
	}

	@Override
	public void editGroupRoute(GroupRouteVO groupRouteVO) {
		saveGroupRoute(groupRouteVO);
	}

	@Override
	public GroupRouteVO findGroupRouteByGroupId(Integer groupId) {
		return groupRouteDal.findGroupRouteByGroupId(groupId);
	}

	@Override
	public GroupRouteVO findGroupRouteByOrderId(Integer orderId) {
		return groupRouteDal.findGroupRouteByOrderId(orderId);
	}

	@Override
	public List<GroupRoute> selectByOrderId(Integer orderId) {
		return groupRouteDal.selectByOrderId(orderId);
	}

	@Override
	public List<GroupRouteSupplier> selectSupplierByGroupIdAndType(
			Integer groupId, Integer type) {

		return groupRouteDal.selectSupplierByGroupIdAndType(groupId,
				type);
	}

	
	@Override
	public List<GroupRoute> selectByGroupIdAndBookingId(Integer groupId,
			Integer bookingId) {
		return groupRouteDal.selectByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public GroupRouteVO findGroupRouteByGroupIdAndSupplierId(Integer groupId,
			Integer supplierId) {
		return groupRouteDal.findGroupRouteByGroupIdAndSupplierId(groupId, supplierId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByGroupId(
			Integer groupId) {
		return groupRouteDal.selectGroupRouteSupplierByGroupId(groupId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByOrderId(
			Integer orderId) {
		return groupRouteDal.selectGroupRouteSupplierByOrderId(orderId);
	}

	@Override
	public List<GroupRouteAttachment> selectAttachmentByGroupId(Integer groupId) {
		return groupRouteDal.selectAttachmentByGroupId(groupId);
	}

	@Override
	public List<GroupRouteAttachment> selectAttachmentByOrderId(Integer orderId) {
		return groupRouteDal.selectAttachmentByOrderId(orderId);
	}
	
	@Override
	public GroupRoute selectDayNumAndMaxday(Integer orderId){
		return groupRouteDal.selectDayNumAndMaxday(orderId);
	}

	
}

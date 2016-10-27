package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.FitGroupDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;

public class FitGroupBizImpl implements FitGroupBiz {

	@Autowired
	private FitGroupDal fitGroupDal;
	

	@Override
	public FitGroupInfoVO selectFitGroupInfoById(Integer groupId) {
		return fitGroupDal.selectFitGroupInfoById(groupId);
//		FitGroupInfoVO fitGroupInfo = new FitGroupInfoVO();
//		TourGroup tourGroup = tourGroupMapper.selectByPrimaryKey(groupId);
//		fitGroupInfo.setTourGroup(tourGroup);
//
//		List<GroupOrder> orderList = groupOrderMapper
//				.selectOrderByGroupId(groupId);
//		fitGroupInfo.setGroupOrderList(orderList);
//		return fitGroupInfo;
	}

	@Override
	public void updateFitGroupInfo(FitGroupInfoVO fitGroupInfoVO,
			Integer userId, String userName) {
		fitGroupDal.updateFitGroupInfo(fitGroupInfoVO, userId, userName);
	}
//		TourGroup tourGroup = fitGroupInfoVO.getTourGroup();
//		List<GroupRouteDayVO> groupRouteDayVOList = fitGroupInfoVO
//				.getGroupRouteDayVOList();
//		tourGroup.setDaynum(groupRouteDayVOList == null ? 1
//				: groupRouteDayVOList.size());
//		tourGroup.setUpdateTime(System.currentTimeMillis());
//		tourGroup.setUpdateId(userId);
//		tourGroup.setUpdateName(userName);
//		tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
//		tourGroup = tourGroupMapper.selectByPrimaryKey(tourGroup.getId());
//
//		List<GroupRoute> groupRouteList = groupRouteMapper
//				.selectByGroupId(tourGroup.getId());
//
//		if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
//			for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {
//
//				// 主表
//				GroupRoute groupRoute = groupRouteDayVO.getGroupRoute();
//
//				groupRoute.setGroupId(tourGroup.getId());
//				Calendar c = Calendar.getInstance();
//				c.setTime(tourGroup.getDateStart());
//				c.add(Calendar.DAY_OF_MONTH, groupRoute.getDayNum() - 1);
//				Date t = c.getTime();
//				groupRoute.setGroupDate(t);
//				if (groupRoute.getId() == null) {
//					groupRoute.setCreateTime(System.currentTimeMillis());
//					groupRouteMapper.insert(groupRoute);
//				} else {
//					groupRouteMapper.updateByPrimaryKeySelective(groupRoute);
//					Iterator<GroupRoute> iterator = groupRouteList.iterator();
//					while (iterator.hasNext()) {
//						GroupRoute next = iterator.next();
//						if (groupRoute.getId().equals(next.getId())) {
//							iterator.remove();
//						}
//					}
//				}
//
//				// 交通
//				List<GroupRouteTraffic> groupRouteTrafficList = groupRouteDayVO
//						.getGroupRouteTrafficList();
//				List<GroupRouteTraffic> TrafficList = groupRouteTrafficMapper
//						.selectGroupRouteTrafficByRouteId(groupRoute.getId());
//				if (groupRouteTrafficList != null
//						&& groupRouteTrafficList.size() > 0) {
//					for (GroupRouteTraffic groupRouteTraffic : groupRouteTrafficList) {
//						groupRouteTraffic.setRouteId(groupRoute.getId());
//						if (groupRouteTraffic.getId() == null) {
//							groupRouteTraffic.setCreateTime(System
//									.currentTimeMillis());
//							groupRouteTrafficMapper.insert(groupRouteTraffic);
//						} else {
//							groupRouteTrafficMapper
//									.updateByPrimaryKeySelective(groupRouteTraffic);
//							Iterator<GroupRouteTraffic> iterator = TrafficList
//									.iterator();
//							while (iterator.hasNext()) {
//								GroupRouteTraffic next = iterator.next();
//								if (groupRouteTraffic.getId().equals(
//										next.getId())) {
//									iterator.remove();
//								}
//							}
//						}
//
//					}
//				}
//				if (TrafficList != null && TrafficList.size() > 0) {
//					for (GroupRouteTraffic grt : TrafficList) {
//						groupRouteTrafficMapper.deleteByPrimaryKey(grt.getId());
//					}
//				}
//
//				// 备选商家
//				List<GroupRouteSupplier> groupRouteOptionsSupplierList = groupRouteDayVO
//						.getGroupRouteOptionsSupplierList();
//				List<GroupRouteSupplier> supplierList = groupRouteSupplierMapper
//						.selectGroupRouteSupplierByRouteId(groupRoute.getId());
//				if (groupRouteOptionsSupplierList != null
//						&& groupRouteOptionsSupplierList.size() > 0) {
//					for (GroupRouteSupplier groupRouteSupplier : groupRouteOptionsSupplierList) {
//
//						groupRouteSupplier.setRouteId(groupRoute.getId());
//						if (groupRouteSupplier.getId() == null) {
//							groupRouteSupplier.setCreateTime(System
//									.currentTimeMillis());
//							groupRouteSupplierMapper.insert(groupRouteSupplier);
//						} else {
//							groupRouteSupplierMapper
//									.updateByPrimaryKeySelective(groupRouteSupplier);
//							Iterator<GroupRouteSupplier> iterator = supplierList
//									.iterator();
//							while (iterator.hasNext()) {
//								GroupRouteSupplier next = iterator.next();
//								if (groupRouteSupplier.getId().equals(
//										next.getId())) {
//									iterator.remove();
//								}
//							}
//						}
//
//					}
//				}
//				if (supplierList != null && supplierList.size() > 0) {
//					for (GroupRouteSupplier grs : supplierList) {
//						groupRouteSupplierMapper
//								.deleteByPrimaryKey(grs.getId());
//					}
//				}
//
//				// 附件
//				List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteDayVO
//						.getGroupRouteAttachmentList();
//				List<GroupRouteAttachment> attachList = groupRouteAttachmentMapper
//						.selectByRouteId(groupRoute.getId());
//				if (groupRouteAttachmentList != null
//						&& groupRouteAttachmentList.size() > 0) {
//					for (GroupRouteAttachment groupRouteAttachment : groupRouteAttachmentList) {
//
//						groupRouteAttachment.setObjId(groupRoute.getId());
//
//						if (groupRouteAttachment.getId() == null) {
//							groupRouteAttachment.setCreateTime(System
//									.currentTimeMillis());
//							groupRouteAttachmentMapper
//									.insert(groupRouteAttachment);
//						} else {
//							groupRouteAttachmentMapper
//									.updateByPrimaryKeySelective(groupRouteAttachment);
//							Iterator<GroupRouteAttachment> iterator = attachList
//									.iterator();
//							while (iterator.hasNext()) {
//								GroupRouteAttachment next = iterator.next();
//								if (groupRouteAttachment.getId().equals(
//										next.getId())) {
//									iterator.remove();
//								}
//							}
//						}
//					}
//
//				}
//				if (attachList != null && attachList.size() > 0) {
//					for (GroupRouteAttachment gra : attachList) {
//						groupRouteAttachmentMapper.deleteByPrimaryKey(gra
//								.getId());
//					}
//				}
//
//			}
//		}
//		if (groupRouteList != null && groupRouteList.size() > 0) {
//			for (GroupRoute gr : groupRouteList) {
//				groupRouteMapper.deleteByPrimaryKey(gr.getId());
//			}
//		}
//
//	}
}

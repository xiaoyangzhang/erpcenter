package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRouteDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteAttachmentMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteTrafficMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;

import org.springframework.transaction.annotation.Transactional;

public class GroupRouteDalImpl implements GroupRouteDal {

	@Autowired
	private GroupRouteSupplierMapper groupRouteSupplierMapper;

	@Autowired
	private GroupRouteTrafficMapper groupRouteTrafficMapper;
	@Autowired
	private GroupRouteAttachmentMapper groupRouteAttachmentMapper;

	@Autowired
	private GroupRouteMapper groupRouteMapper;
	@Autowired
	private TourGroupMapper tourGroupMapper;
	@Autowired
	private GroupOrderMapper groupOrderMapper;

	@Override
	public List<GroupRoute> selectByGroupId(Integer groupId) {
		return groupRouteMapper.selectByGroupId(groupId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByRouteId(
			Integer groupRouteId) {
		return groupRouteSupplierMapper
				.selectGroupRouteSupplierByRouteId(groupRouteId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByRouteIdAndType(
			Integer groupRouteId,Integer type) {
		return groupRouteSupplierMapper
				.selectGroupRouteSupplierByRouteIdAndType(groupRouteId,type);
	}


	@Override
	public List<GroupRouteTraffic> selectGroupRouteTrafficByRouteId(
			Integer groupRouteId) {
		return groupRouteTrafficMapper
				.selectGroupRouteTrafficByRouteId(groupRouteId);
	}

	@Transactional
	@Override
	public void saveGroupRoute(GroupRouteVO groupRouteVO) {

		List<GroupRouteDayVO> groupRouteDayVOList = groupRouteVO
				.getGroupRouteDayVOList();
		TourGroup tourGroup = groupRouteVO.getTourGroup();
		GroupOrder groupOrder = null;
		
		// 更新天数
		if (tourGroup != null
				&& tourGroup.getId() != null) {
			//add by gejinjun 2015-11-3 为防止团行程重复添加，先删除该订单下的行程
			groupRouteMapper.deleteByGroupId(tourGroup.getId());
			
			TourGroup t = tourGroupMapper.selectByPrimaryKey(tourGroup.getId());
			tourGroup = groupRouteVO.getTourGroup();
			tourGroup.setDaynum(groupRouteDayVOList==null?0:groupRouteDayVOList.size());
			Date startTime = t.getDateStart();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			cal.add(Calendar.DAY_OF_MONTH, +groupRouteDayVOList.size() - 1);
			Date time = cal.getTime();
			tourGroup.setDateEnd(time);
			tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
			tourGroup.setDateStart(t.getDateStart());
		}

		if (groupRouteVO.getOrderId() != null) {
			groupOrder = groupOrderMapper.selectByPrimaryKey(groupRouteVO
					.getOrderId());
			groupOrder.setProductName(groupRouteVO.getTourGroup()
					.getProductName());
			groupOrder.setProductBrandName(groupRouteVO.getTourGroup()
					.getProductBrandName());
			groupOrderMapper.updateByPrimaryKeySelective(groupOrder);

		}

		List<GroupRoute> groupRouteList = groupRouteMapper.selectByOrderId(groupOrder.getId());
		if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
			
			for (int i = 0; i < groupRouteDayVOList.size(); i++) {
			
				// 主表
				GroupRoute groupRoute = groupRouteDayVOList.get(i).getGroupRoute();
				
				if (tourGroup != null) {
					groupRoute.setGroupId(tourGroup.getId());
				}
				if(groupRoute.getGroupDate()==null && tourGroup!=null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(tourGroup.getDateStart());
					cal.add(Calendar.DAY_OF_MONTH, +i);
					Date time = cal.getTime();
					groupRoute.setGroupDate(time);
					groupRoute.setDayNum(i+1);
				}
				
				
				if (groupOrder != null) {
					groupRoute.setOrderId(groupOrder.getId());
				}
				if(groupRoute.getId()==null){
					groupRoute.setCreateTime(System.currentTimeMillis());
					groupRouteMapper.insert(groupRoute);
				}else{
					groupRouteMapper.updateByPrimaryKeySelective(groupRoute);
					Iterator<GroupRoute> iterator = groupRouteList.iterator();
					while (iterator.hasNext()) {
						GroupRoute next = iterator.next();
						if (groupRoute.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
				
				// 交通
				List<GroupRouteTraffic> groupRouteTrafficList = groupRouteDayVOList.get(i)
						.getGroupRouteTrafficList();
				List<GroupRouteTraffic> TrafficList = groupRouteTrafficMapper
						.selectGroupRouteTrafficByRouteId(groupRoute.getId());
				if (groupRouteTrafficList != null
						&& groupRouteTrafficList.size() > 0) {
					for (GroupRouteTraffic groupRouteTraffic : groupRouteTrafficList) {
						groupRouteTraffic.setRouteId(groupRoute.getId());
						if(groupRouteTraffic.getId()==null){
							groupRouteTraffic.setCreateTime(System
									.currentTimeMillis());
							groupRouteTrafficMapper.insert(groupRouteTraffic);
						}else{
							groupRouteTrafficMapper.updateByPrimaryKeySelective(groupRouteTraffic);
							Iterator<GroupRouteTraffic> iterator = TrafficList
									.iterator();
							while (iterator.hasNext()) {
								GroupRouteTraffic next = iterator.next();
								if (groupRouteTraffic.getId().equals(
										next.getId())) {
									iterator.remove();
								}
							}
						}
					

					}
				}
				if (TrafficList != null && TrafficList.size() > 0) {
					for (GroupRouteTraffic grt : TrafficList) {
						groupRouteTrafficMapper.deleteByPrimaryKey(grt.getId());
					}
				}
				// 景区
//				List<GroupRouteSupplier> groupRouteScenicSupplierList = groupRouteDayVOList.get(i)
//						.getGroupRouteScenicSupplierList();
//				if (groupRouteScenicSupplierList != null
//						&& groupRouteScenicSupplierList.size() > 0) {
//					for (GroupRouteSupplier groupRouteSupplier : groupRouteScenicSupplierList) {
//						groupRouteSupplier.setCreateTime(System
//								.currentTimeMillis());
//						groupRouteSupplier.setRouteId(groupRoute.getId());
//						groupRouteSupplier.setIsOption(0);
//						groupRouteSupplierMapper.insert(groupRouteSupplier);
//					}
//				}
				// 备选商家
				List<GroupRouteSupplier> groupRouteOptionsSupplierList = groupRouteDayVOList.get(i)
						.getGroupRouteOptionsSupplierList();
				List<GroupRouteSupplier> supplierList = groupRouteSupplierMapper
						.selectGroupRouteSupplierByRouteId(groupRoute.getId());
				if (groupRouteOptionsSupplierList != null
						&& groupRouteOptionsSupplierList.size() > 0) {
					for (GroupRouteSupplier groupRouteSupplier : groupRouteOptionsSupplierList) {

						groupRouteSupplier.setRouteId(groupRoute.getId());
						if (groupRouteSupplier.getId() == null) {
							groupRouteSupplier.setCreateTime(System
									.currentTimeMillis());
							groupRouteSupplierMapper.insert(groupRouteSupplier);
						} else {
							groupRouteSupplierMapper
									.updateByPrimaryKeySelective(groupRouteSupplier);
							Iterator<GroupRouteSupplier> iterator = supplierList
									.iterator();
							while (iterator.hasNext()) {
								GroupRouteSupplier next = iterator.next();
								if (groupRouteSupplier.getId().equals(
										next.getId())) {
									iterator.remove();
								}
							}
						}

					}

				}
				if (supplierList != null && supplierList.size() > 0) {
					for (GroupRouteSupplier grs : supplierList) {
						groupRouteSupplierMapper
								.deleteByPrimaryKey(grs.getId());
					}
				}
				 //附件
				List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteDayVOList.get(i)
						.getGroupRouteAttachmentList();
				List<GroupRouteAttachment> attachList = groupRouteAttachmentMapper
						.selectByRouteId(groupRoute.getId());
				if (groupRouteAttachmentList != null
						&& groupRouteAttachmentList.size() > 0) {
					for (GroupRouteAttachment groupRouteAttachment : groupRouteAttachmentList) {

						groupRouteAttachment.setObjId(groupRoute.getId());

						if (groupRouteAttachment.getId() == null) {
							groupRouteAttachment.setCreateTime(System
									.currentTimeMillis());
							groupRouteAttachmentMapper
									.insert(groupRouteAttachment);
						} else {
							groupRouteAttachmentMapper
									.updateByPrimaryKeySelective(groupRouteAttachment);
							Iterator<GroupRouteAttachment> iterator = attachList
									.iterator();
							while (iterator.hasNext()) {
								GroupRouteAttachment next = iterator.next();
								if (groupRouteAttachment.getId().equals(
										next.getId())) {
									iterator.remove();
								}
							}
						}
					
					}

				}
				if (attachList != null && attachList.size() > 0) {
					for (GroupRouteAttachment gra : attachList) {
						groupRouteAttachmentMapper.deleteByPrimaryKey(gra
								.getId());
					}
				}

			}

		}

	}

	@Transactional
	@Override
	public void editGroupRoute(GroupRouteVO groupRouteVO) {
		saveGroupRoute(groupRouteVO);
	}

	@Override
	public GroupRouteVO findGroupRouteByGroupId(Integer groupId) {
		GroupRouteVO groupRouteVO = new GroupRouteVO();
		List<GroupRoute> groupRouteList = groupRouteMapper
				.selectByGroupId(groupId);
		List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();

		if (groupRouteList != null && groupRouteList.size() > 0) {
			for (GroupRoute groupRoute : groupRouteList) {
				GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
				groupRouteDayVO.setGroupRoute(groupRoute);
				List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteAttachmentMapper
						.selectByRouteIdAndType(groupRoute.getId(), 2, 1);
				groupRouteDayVO
						.setGroupRouteAttachmentList(groupRouteAttachmentList);

				List<GroupRouteTraffic> groupRouteTrafficList = groupRouteTrafficMapper
						.selectGroupRouteTrafficByRouteId(groupRoute.getId());
				groupRouteDayVO.setGroupRouteTrafficList(groupRouteTrafficList);

				List<GroupRouteSupplier> groupRouteSupplierList = groupRouteSupplierMapper
						.selectGroupRouteSupplierByRouteId(groupRoute.getId());

//				List<GroupRouteSupplier> groupRouteScenicSupplierList = new ArrayList<GroupRouteSupplier>();

				List<GroupRouteSupplier> groupRouteOptionsSupplierList = new ArrayList<GroupRouteSupplier>();
				if (groupRouteSupplierList != null
						&& groupRouteSupplierList.size() > 0) {
					for (GroupRouteSupplier groupRouteSupplier : groupRouteSupplierList) {
//						if (groupRouteSupplier.getIsOption() == 1) {
							groupRouteOptionsSupplierList
									.add(groupRouteSupplier);
//						} else {
//							groupRouteScenicSupplierList
//									.add(groupRouteSupplier);
//						}
					}

				}
				groupRouteDayVO
						.setGroupRouteOptionsSupplierList(groupRouteOptionsSupplierList);
//				groupRouteDayVO
//						.setGroupRouteScenicSupplierList(groupRouteScenicSupplierList);
				groupRouteDayVOList.add(groupRouteDayVO);
			}
		}
		groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);
		return groupRouteVO;
	}

	@Override
	public GroupRouteVO findGroupRouteByOrderId(Integer orderId) {
		GroupRouteVO groupRouteVO = new GroupRouteVO();
		List<GroupRoute> groupRouteList = groupRouteMapper.selectByOrderId(orderId);
		List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();

		if (groupRouteList != null && groupRouteList.size() > 0) {
			for (GroupRoute groupRoute : groupRouteList) {
				GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
				groupRouteDayVO.setGroupRoute(groupRoute);
				List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteAttachmentMapper
						.selectByRouteIdAndType(groupRoute.getId(), 2, 1);
				groupRouteDayVO
						.setGroupRouteAttachmentList(groupRouteAttachmentList);

				List<GroupRouteTraffic> groupRouteTrafficList = groupRouteTrafficMapper
						.selectGroupRouteTrafficByRouteId(groupRoute.getId());
				groupRouteDayVO.setGroupRouteTrafficList(groupRouteTrafficList);

				List<GroupRouteSupplier> groupRouteSupplierList = groupRouteSupplierMapper
						.selectGroupRouteSupplierByRouteId(groupRoute.getId());

				List<GroupRouteSupplier> groupRouteOptionsSupplierList = new ArrayList<GroupRouteSupplier>();
				if (groupRouteSupplierList != null
						&& groupRouteSupplierList.size() > 0) {
					for (GroupRouteSupplier groupRouteSupplier : groupRouteSupplierList) {
							groupRouteOptionsSupplierList
									.add(groupRouteSupplier);
					}

				}
				groupRouteDayVO
						.setGroupRouteOptionsSupplierList(groupRouteOptionsSupplierList);

				groupRouteDayVOList.add(groupRouteDayVO);
			}
		}
		groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);
		return groupRouteVO;
	}

	@Override
	public List<GroupRoute> selectByOrderId(Integer orderId) {
		return groupRouteMapper.selectByOrderId(orderId);
	}

	@Override
	public List<GroupRouteSupplier> selectSupplierByGroupIdAndType(
			Integer groupId, Integer type) {

		return groupRouteSupplierMapper.selectSupplierByGroupIdAndType(groupId,
				type);
	}

	
	@Override
	public List<GroupRoute> selectByGroupIdAndBookingId(Integer groupId,
			Integer bookingId) {
		return groupRouteMapper.selectByGroupIdAndBookingId(groupId, bookingId);
	}

	@Override
	public GroupRouteVO findGroupRouteByGroupIdAndSupplierId(Integer groupId,
			Integer supplierId) {
		GroupRouteVO groupRouteVO = new GroupRouteVO();
		List<GroupRoute> groupRouteList = groupRouteMapper.selectByGroupIdAndSupplierId(groupId,supplierId);
		List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();

		if (groupRouteList != null && groupRouteList.size() > 0) {
			for (GroupRoute groupRoute : groupRouteList) {
				GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
				groupRouteDayVO.setGroupRoute(groupRoute);
				groupRouteDayVOList.add(groupRouteDayVO);
			}
		}
		groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);
		return groupRouteVO;
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByGroupId(
			Integer groupId) {
		return groupRouteSupplierMapper.selectGroupRouteSupplierByGroupId(groupId);
	}

	@Override
	public List<GroupRouteSupplier> selectGroupRouteSupplierByOrderId(
			Integer orderId) {
		return groupRouteSupplierMapper.selectGroupRouteSupplierByOrderId(orderId);
	}

	@Override
	public List<GroupRouteAttachment> selectAttachmentByGroupId(Integer groupId) {
		return groupRouteAttachmentMapper.selectAttachmentByGroupId(groupId);
	}

	@Override
	public List<GroupRouteAttachment> selectAttachmentByOrderId(Integer orderId) {
		return groupRouteAttachmentMapper.selectAttachmentByOrderId(orderId);
	}
	
	@Override
	public GroupRoute selectDayNumAndMaxday(Integer orderId){
		return groupRouteMapper.selectDayNumAndMaxday(orderId);
	}

	
}

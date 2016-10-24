package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketRequestDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TeamGroupDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TourGroupDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderGuestMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderPriceMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderTransportMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRequirementMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteAttachmentMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteTrafficMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;


public class TeamGroupDalImpl implements TeamGroupDal {
	private static final Logger log = LoggerFactory
			.getLogger(TeamGroupDalImpl.class);
	@Autowired
	private GroupOrderMapper groupOrderMapper;
	@Autowired
	private TourGroupMapper tourGroupMapper;
	@Autowired
	private GroupRouteAttachmentMapper groupRouteAttachmentMapper;
	@Autowired
	private GroupRouteSupplierMapper groupRouteSupplierMapper;
	@Autowired
	private GroupRouteTrafficMapper groupRouteTrafficMapper;
	@Autowired
	private GroupRouteMapper groupRouteMapper;
	@Autowired
	private GroupOrderTransportMapper groupOrderTransportMapper;
	@Autowired
	private GroupOrderGuestMapper groupOrderGuestMapper;
	@Autowired
	private GroupOrderPriceMapper groupOrderPriceMapper;
	@Autowired
	private GroupRequirementMapper groupRequirementMapper;
	@Autowired
	private TourGroupDal tourGroupDal;
	@Autowired
	private GroupOrderDal groupOrderDal;
	@Autowired
	private AirTicketRequestDal airTicketRequestDal;
	
	@Override
	public TeamGroupVO selectTeamGroupVOByGroupId(Integer groupId, Integer bizId) {
		// TODO Auto-generated method stub
		TeamGroupVO teamGroupVO = new TeamGroupVO();
		TourGroup tourGroup = tourGroupMapper.selectByPrimaryKey(groupId);
		teamGroupVO.setTourGroup(tourGroup);

		List<GroupOrder> orderListByGroupId = groupOrderMapper
				.selectOrderByGroupId(groupId);
		GroupOrder GroupOrder = orderListByGroupId.get(0);
		teamGroupVO.setGroupOrder(orderListByGroupId.get(0));

		// 接送信息
		List<GroupOrderTransport> transList = groupOrderTransportMapper
				.selectByOrderId(GroupOrder.getId());
		teamGroupVO.setGroupOrderTransportList(transList);

		// 成本
		List<GroupOrderPrice> outList = groupOrderPriceMapper
				.selectByOrderAndType(GroupOrder.getId(), 1);
		teamGroupVO.setGroupOrderCostList(outList);
		// 收入
		List<GroupOrderPrice> inList = groupOrderPriceMapper
				.selectByOrderAndType(GroupOrder.getId(), 0);
		teamGroupVO.setGroupOrderPriceList(inList);
		// 客人
		List<GroupOrderGuest> guestList = groupOrderGuestMapper
				.selectByOrderId(GroupOrder.getId());
		List<Integer> guestIdList = airTicketRequestDal
				.findIssuedGuestIdList(GroupOrder.getBizId(),
						GroupOrder.getId());
		teamGroupVO.setGroupOrderGuestList(guestList);
		if (guestList != null && guestList.size() > 0) {
			for (GroupOrderGuest groupOrderGuest : guestList) {
				groupOrderGuest.setEditType(!guestIdList
						.contains(groupOrderGuest.getId()));
			}
		}

		return teamGroupVO;
	}

	@Override
	public TeamGroupVO saveOrUpdateTeamGroupVO(Integer bizId, Integer userId,
			String userName, TeamGroupVO teamGroupVO) throws ParseException {
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TourGroup tourGroup = teamGroupVO.getTourGroup();
		GroupOrder groupOrder = teamGroupVO.getGroupOrder();
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(groupOrder.getDepartureDate()));
		cal.add(Calendar.DAY_OF_MONTH,
				+(teamGroupVO.getGroupRouteDayVOList() == null ? 0
						: teamGroupVO.getGroupRouteDayVOList().size() - 1));
		Date time = cal.getTime();
		tourGroup.setDateEnd(time);
		tourGroup.setDateStart(sdf.parse(groupOrder.getDepartureDate()));
		tourGroup.setDaynum(teamGroupVO.getGroupRouteDayVOList() == null ? 0
				: teamGroupVO.getGroupRouteDayVOList().size());
		tourGroup.setOperatorId(groupOrder.getOperatorId());
		tourGroup.setOperatorName(groupOrder.getOperatorName());
		tourGroup.setProductBrandName(groupOrder.getProductBrandName());
		tourGroup.setPrudctBrandId(groupOrder.getProductBrandId());
		tourGroup.setProductId(groupOrder.getProductId());
		tourGroup.setProductName(groupOrder.getProductName());
		// tourGroup.setRemark(groupOrder.getRemark());
		// tourGroup.setRemarkInternal(groupOrder.getRemarkInternal());
		tourGroup.setTotalAdult(groupOrder.getNumAdult());
		tourGroup.setTotalChild(groupOrder.getNumChild());
		tourGroup.setTotalGuide(groupOrder.getNumGuide());
		
		if (tourGroup.getId() == null && groupOrder.getId() == null) {

			String makeCodeByMode = "";
			
			if(teamGroupVO.isAgency()){
				TourGroup groupCodeSort = tourGroupMapper.selectGroupCodeSort(bizId,null,sdf.format(tourGroup.getDateStart()));
				tourGroup.setGroupCodeSort(groupCodeSort == null ? 1: groupCodeSort.getGroupCodeSort() + 1);
				
				makeCodeByMode = tourGroupDal.makeCodeForAgency(tourGroup.getGroupCode(), 
						teamGroupVO.getProductCode(), sdf.format(tourGroup.getDateStart()), sdf.format(tourGroup.getDateEnd()), tourGroup.getGroupCodeMark(), 
						groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
			}else{
				TourGroup groupCodeSort = tourGroupMapper.selectGroupCodeSort(bizId,1,
						sdf.format(tourGroup.getDateStart()));
				tourGroup.setGroupCodeSort(groupCodeSort == null ? 1
						: groupCodeSort.getGroupCodeSort() + 1);
				
				makeCodeByMode = tourGroupDal.makeCodeByMode(tourGroup
						.getGroupCode(), 1, sdf.format(tourGroup.getDateStart()),
						tourGroup.getGroupCodeMark(), groupCodeSort == null ? 1
								: groupCodeSort.getGroupCodeSort() + 1);
			}
				
			tourGroup.setGroupCode(makeCodeByMode);

			GroupOrder orderCodeSort = groupOrderMapper
					.selectGroupOrderCodeSort(tourGroup.getBizId(),
							sdf.format(tourGroup.getDateStart()));
			makeCodeByMode = groupOrderDal.makeCodeByMode(groupOrder
					.getBizId(), groupOrder.getOrderNo(), sdf.format(tourGroup
					.getDateStart()),
					orderCodeSort == null ? 1
							: orderCodeSort.getOrderNoSort() + 1);
			groupOrder.setOrderNo(makeCodeByMode);
			groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort
					.getOrderNoSort() + 1);

			groupOrder.setBizId(bizId);
			groupOrder.setState(1);
			groupOrder.setOrderType(1);
			groupOrder.setCreatorId(userId);
			groupOrder.setCreatorName(userName);
			groupOrder.setCreateTime(System.currentTimeMillis());

			tourGroup.setBizId(bizId);
			tourGroup.setGroupState(0); // 未确认
			tourGroup.setOrderNum(1);
			tourGroup.setCreateTime(System.currentTimeMillis());

			tourGroupMapper.insert(tourGroup);
			groupOrder.setGroupId(tourGroup.getId());
			groupOrderMapper.insert(groupOrder);
			
			//产生日志
			
		} else {
			tourGroupDal.updateByPrimaryKeySelective(tourGroup);
		}
		
		// 团里面的备注、服务标准、内部备注存一份到订单--只针对定制团队
		groupOrder.setRemark(tourGroup.getRemark());
		groupOrder.setServiceStandard(tourGroup.getServiceStandard());
		groupOrder.setRemarkInternal(tourGroup.getRemarkInternal());
		groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
		
		
		
		// 成本
		List<GroupOrderPrice> outcomeList = teamGroupVO.getGroupOrderCostList();
		List<GroupOrderPrice> oList = groupOrderPriceMapper.selectByOrderAndType(groupOrder.getId(), 1);

		if (outcomeList != null && outcomeList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : outcomeList) {
				if (groupOrderPrice.getId() == null) {
					groupOrderPrice.setOrderId(groupOrder.getId());
					groupOrderPrice.setCreatorId(userId);
					groupOrderPrice.setCreatorName(userName);
					groupOrderPrice.setCreateTime(System.currentTimeMillis());
					groupOrderPrice.setTotalPrice(groupOrderPrice.getNumPerson() * groupOrderPrice.getNumTimes() * groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper.insert(groupOrderPrice);
				} else {
					groupOrderPrice.setTotalPrice(groupOrderPrice.getNumPerson() * groupOrderPrice.getNumTimes() * groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper.updateByPrimaryKeySelective(groupOrderPrice);

					Iterator<GroupOrderPrice> iterator = oList.iterator();
					while (iterator.hasNext()) {
						GroupOrderPrice next = iterator.next();
						if (groupOrderPrice.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}
		if (oList != null && oList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : oList) {
				groupOrderPriceMapper.deleteByPrimaryKey(groupOrderPrice.getId());
			}
		}

		// 收入

		List<GroupOrderPrice> incomeList = teamGroupVO.getGroupOrderPriceList();
		List<GroupOrderPrice> iList = groupOrderPriceMapper
				.selectByOrderAndType(groupOrder.getId(), 0);
		if (incomeList != null && incomeList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : incomeList) {
				if (groupOrderPrice.getId() == null) {
					groupOrderPrice.setOrderId(groupOrder.getId());
					groupOrderPrice.setCreatorId(userId);
					groupOrderPrice.setCreatorName(userName);
					groupOrderPrice.setCreateTime(System.currentTimeMillis());
					groupOrderPrice.setTotalPrice(groupOrderPrice
							.getNumPerson()
							* groupOrderPrice.getNumTimes()
							* groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper.insert(groupOrderPrice);
				} else {
					groupOrderPrice.setTotalPrice(groupOrderPrice
							.getNumPerson()
							* groupOrderPrice.getNumTimes()
							* groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper
							.updateByPrimaryKeySelective(groupOrderPrice);
					Iterator<GroupOrderPrice> iterator = iList.iterator();
					while (iterator.hasNext()) {
						GroupOrderPrice next = iterator.next();
						if (groupOrderPrice.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}
		if (iList != null && iList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : iList) {
				groupOrderPriceMapper.deleteByPrimaryKey(groupOrderPrice
						.getId());
			}
		}
		groupOrderDal.updateOrderAndGroupPrice(groupOrder.getId());
		// 接送信息

		List<GroupOrderTransport> transportList = teamGroupVO
				.getGroupOrderTransportList();
		List<GroupOrderTransport> transList = groupOrderTransportMapper
				.selectByOrderId(groupOrder.getId());
		if (transportList != null && transportList.size() > 0) {
			for (GroupOrderTransport groupOrderTransport : transportList) {
				if (groupOrderTransport.getId() == null) {
					groupOrderTransport.setOrderId(groupOrder.getId());
					groupOrderTransport.setCreateTime(System
							.currentTimeMillis());
					groupOrderTransportMapper.insert(groupOrderTransport);
				} else {
					groupOrderTransportMapper
							.updateByPrimaryKeySelective(groupOrderTransport);
					Iterator<GroupOrderTransport> iterator = transList
							.iterator();
					while (iterator.hasNext()) {
						GroupOrderTransport next = iterator.next();
						if (groupOrderTransport.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}
		if (transList != null && transList.size() > 0) {
			for (GroupOrderTransport groupOrderTransport : transList) {
				groupOrderTransportMapper
						.deleteByPrimaryKey(groupOrderTransport.getId());
			}
		}

		// 客人

		List<GroupOrderGuest> guestList = teamGroupVO.getGroupOrderGuestList();
		List<GroupOrderGuest> tList = groupOrderGuestMapper
				.selectByOrderId(groupOrder.getId());
		if (guestList != null && guestList.size() > 0) {
			for (GroupOrderGuest groupOrderGuest : guestList) {
				if (groupOrderGuest.getId() == null) {
					groupOrderGuest.setOrderId(groupOrder.getId());
					groupOrderGuest.setCreateTime(System.currentTimeMillis());
					groupOrderGuestMapper.insert(groupOrderGuest);
				} else {
					groupOrderGuestMapper
							.updateByPrimaryKeySelective(groupOrderGuest);
					Iterator<GroupOrderGuest> iterator = tList.iterator();
					while (iterator.hasNext()) {
						GroupOrderGuest next = iterator.next();
						if (groupOrderGuest.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}

		}
		if (tList != null && tList.size() > 0) {
			for (GroupOrderGuest groupOrderGuest : tList) {
				groupOrderGuestMapper.deleteByPrimaryKey(groupOrderGuest
						.getId());
			}
		}

		// 行程

		List<GroupRoute> groupRouteList = groupRouteMapper
				.selectByGroupId(tourGroup.getId());
		List<GroupRouteDayVO> groupRouteDayVOList = teamGroupVO
				.getGroupRouteDayVOList();

		if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
			for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {

				// 主表
				GroupRoute groupRoute = groupRouteDayVO.getGroupRoute();

				groupRoute.setOrderId(groupOrder.getId());
				groupRoute.setGroupId(tourGroup.getId());

				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(groupOrder.getDepartureDate()));
				c.add(Calendar.DAY_OF_MONTH, groupRoute.getDayNum() - 1);
				Date t = c.getTime();
				groupRoute.setGroupDate(t);
				if (groupRoute.getId() == null) {
					groupRoute.setCreateTime(System.currentTimeMillis());
					groupRouteMapper.insert(groupRoute);
				} else {
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
				List<GroupRouteTraffic> groupRouteTrafficList = groupRouteDayVO
						.getGroupRouteTrafficList();
				List<GroupRouteTraffic> TrafficList = groupRouteTrafficMapper
						.selectGroupRouteTrafficByRouteId(groupRoute.getId());
				if (groupRouteTrafficList != null
						&& groupRouteTrafficList.size() > 0) {
					for (GroupRouteTraffic groupRouteTraffic : groupRouteTrafficList) {
						groupRouteTraffic.setRouteId(groupRoute.getId());

						if (groupRouteTraffic.getId() == null) {
							groupRouteTraffic.setCreateTime(System
									.currentTimeMillis());
							groupRouteTrafficMapper.insert(groupRouteTraffic);
						} else {
							groupRouteTrafficMapper
									.updateByPrimaryKeySelective(groupRouteTraffic);
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
				// 备选商家
				List<GroupRouteSupplier> groupRouteOptionsSupplierList = groupRouteDayVO
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

				List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteDayVO
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
		if (groupRouteList != null && groupRouteList.size() > 0) {
			for (GroupRoute gr : groupRouteList) {
				groupRouteMapper.deleteByPrimaryKey(gr.getId());
			}
		}
		
		 
		
		return teamGroupVO;
	}

	@Override
	public TeamGroupVO saveOrUpdateRequirement(TeamGroupVO teamGroupVO,
			Integer userId, String userName) {
		// TODO Auto-generated method stub
		TourGroup tourGroup = teamGroupVO.getTourGroup();
		GroupOrder groupOrder = teamGroupVO.getGroupOrder();
		// 酒店

		List<GroupRequirement> hotelAddList = teamGroupVO
				.getHotelGroupRequirementList();
		List<GroupRequirement> hotellist = groupRequirementMapper
				.selectByGroupIdAndType(tourGroup.getId(), 3);
		if (hotelAddList != null && hotelAddList.size() > 0) {
			for (GroupRequirement groupRequirement : hotelAddList) {
				if (groupRequirement.getId() == null) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirement.setOrderId(groupOrder.getId());
					groupRequirement.setCreatorId(userId);
					groupRequirement.setCreatorName(userName);
					groupRequirement.setCreateTime(System.currentTimeMillis());
					groupRequirementMapper.insert(groupRequirement);
				} else {
					groupRequirementMapper
							.updateByPrimaryKeySelective(groupRequirement);
					Iterator<GroupRequirement> iterator = hotellist.iterator();
					while (iterator.hasNext()) {
						GroupRequirement next = iterator.next();
						if (groupRequirement.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}

		if (hotellist != null && hotellist.size() > 0) {
			for (GroupRequirement groupRequirement : hotellist) {
				groupRequirementMapper.deleteByPrimaryKey(groupRequirement
						.getId());
			}
		}
		// 车队

		List<GroupRequirement> fleetAddList = teamGroupVO
				.getFleetGroupRequirementList();
		List<GroupRequirement> fleetlist = groupRequirementMapper
				.selectByGroupIdAndType(tourGroup.getId(), 4);
		if (fleetAddList != null && fleetAddList.size() > 0) {
			for (GroupRequirement groupRequirement : fleetAddList) {
				if (groupRequirement.getId() == null) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirement.setOrderId(groupOrder.getId());
					groupRequirement.setCreatorId(userId);
					groupRequirement.setCreatorName(userName);
					groupRequirement.setCreateTime(System.currentTimeMillis());
					groupRequirementMapper.insert(groupRequirement);
				} else {
					groupRequirementMapper
							.updateByPrimaryKeySelective(groupRequirement);
					Iterator<GroupRequirement> iterator = fleetlist.iterator();
					while (iterator.hasNext()) {
						GroupRequirement next = iterator.next();
						if (groupRequirement.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}
		if (fleetlist != null && fleetlist.size() > 0) {
			for (GroupRequirement groupRequirement : fleetlist) {
				groupRequirementMapper.deleteByPrimaryKey(groupRequirement
						.getId());
			}
		}

		// 导游

		List<GroupRequirement> guideAddList = teamGroupVO
				.getGuideGroupRequirementList();
		List<GroupRequirement> guidelist = groupRequirementMapper
				.selectByGroupIdAndType(tourGroup.getId(), 8);
		if (guideAddList != null && guideAddList.size() > 0) {
			for (GroupRequirement groupRequirement : guideAddList) {
				if (groupRequirement.getId() == null) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirement.setOrderId(groupOrder.getId());
					groupRequirement.setCreatorId(userId);
					groupRequirement.setCreatorName(userName);
					groupRequirement.setCreateTime(System.currentTimeMillis());
					groupRequirementMapper.insert(groupRequirement);
				} else {
					groupRequirementMapper
							.updateByPrimaryKeySelective(groupRequirement);
					Iterator<GroupRequirement> iterator = guidelist.iterator();
					while (iterator.hasNext()) {
						GroupRequirement next = iterator.next();
						if (groupRequirement.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}
		if (guidelist != null && guidelist.size() > 0) {
			for (GroupRequirement groupRequirement : guidelist) {
				groupRequirementMapper.deleteByPrimaryKey(groupRequirement
						.getId());
			}
		}
		// 餐厅

		List<GroupRequirement> restAddList = teamGroupVO
				.getRestaurantGroupRequirementList();
		List<GroupRequirement> restlist = groupRequirementMapper
				.selectByGroupIdAndType(tourGroup.getId(), 2);
		if (restAddList != null && restAddList.size() > 0) {
			for (GroupRequirement groupRequirement : restAddList) {
				if (groupRequirement.getId() == null) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirement.setOrderId(groupOrder.getId());
					groupRequirement.setCreatorId(userId);
					groupRequirement.setCreatorName(userName);
					groupRequirement.setCreateTime(System.currentTimeMillis());
					groupRequirementMapper.insert(groupRequirement);
				} else {
					groupRequirementMapper
							.updateByPrimaryKeySelective(groupRequirement);
					Iterator<GroupRequirement> iterator = restlist.iterator();
					while (iterator.hasNext()) {
						GroupRequirement next = iterator.next();
						if (groupRequirement.getId().equals(next.getId())) {
							iterator.remove();
						}
					}
				}
			}
		}
		if (restlist != null && restlist.size() > 0) {
			for (GroupRequirement groupRequirement : restlist) {
				groupRequirementMapper.deleteByPrimaryKey(groupRequirement
						.getId());
			}
		}
		return teamGroupVO;

	}

}

package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierService;
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
import com.yimayhd.erpcenter.dal.sales.client.sales.service.FitOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
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

public class FitOrderDalImpl implements FitOrderDal {
	@Autowired
	private GroupOrderMapper groupOrderMapper;
	@Autowired
	private GroupOrderTransportMapper groupOrderTransportMapper;
	@Autowired
	private GroupOrderGuestMapper groupOrderGuestMapper;
	@Autowired
	private GroupOrderPriceMapper groupOrderPriceMapper;
	@Autowired
	private GroupRequirementMapper groupRequirementMapper;
	@Autowired
	private GroupRouteAttachmentMapper groupRouteAttachmentMapper;
	@Autowired
	private GroupRouteSupplierMapper groupRouteSupplierMapper;
	@Autowired
	private GroupRouteTrafficMapper groupRouteTrafficMapper;
	@Autowired
	private GroupRouteMapper groupRouteMapper;
	@Autowired
	private GroupOrderService groupOrderService;
	@Autowired
	private FinanceService financeService;
	@Autowired
	private AirTicketRequestService airTicketRequestService;
	@Autowired
	private TourGroupMapper tourGroupMapper;
	@Autowired
	private TourGroupService tourGroupService;
	@Autowired
	private GroupRouteService groupRouteService;
	@Autowired
	private BookingSupplierService bookingSupplierService;

	@Override
	public Integer saveOrUpdateFitOrderInfo(FitOrderVO fitOrderVO,
			Integer userId, String userName,Integer proOperId,String proOperName, Integer bizId, String bizCode,
			boolean mergeGroup) throws ParseException {
		String type = "";

		// 订单
		GroupOrder groupOrder = fitOrderVO.getGroupOrder();
		if (groupOrder.getId() == null) {
			type = "add";
			groupOrder.setBizId(bizId);
			groupOrder.setCreateTime(System.currentTimeMillis());
			groupOrder.setState(1);
			groupOrder.setCreatorId(userId);
			groupOrder.setCreatorName(userName);
			GroupOrder orderCodeSort = groupOrderMapper
					.selectGroupOrderCodeSort(bizId,
							groupOrder.getDepartureDate());
			String makeCodeByMode = groupOrderService.makeCodeByMode(bizId,
					groupOrder.getOrderNo(), groupOrder.getDepartureDate(),
					orderCodeSort == null ? 1
							: orderCodeSort.getOrderNoSort() + 1);
			groupOrder.setOrderNo(makeCodeByMode);
			groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort
					.getOrderNoSort() + 1);
			groupOrderMapper.insert(groupOrder);

		} else {
			groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
		}
		
		
		
		groupOrder=groupOrderMapper.selectByPrimaryKey(groupOrder.getId());
		
		if(groupOrder.getGroupId()!=null){
			groupOrderService.updateGroupPersonNum(groupOrder.getGroupId());
		}
		
		// 酒店
		GroupRequirement hotelInfo = fitOrderVO.getHotelInfo();
		if (hotelInfo.getId() == null) {
			hotelInfo.setOrderId(groupOrder.getId());
			hotelInfo.setCreatorId(userId);
			hotelInfo.setCreatorName(userName);
			hotelInfo.setCreateTime(System.currentTimeMillis());
			groupRequirementMapper.insert(hotelInfo);
		} else {
			groupRequirementMapper.updateByPrimaryKey(hotelInfo);
		}
		// 成本
		List<GroupOrderPrice> outcomeList = fitOrderVO.getGroupOrderCostList();
		List<GroupOrderPrice> oList = groupOrderPriceMapper
				.selectByOrderAndType(groupOrder.getId(), 1);

		if (outcomeList != null && outcomeList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : outcomeList) {
				if (groupOrderPrice.getId() == null) {
					groupOrderPrice.setOrderId(groupOrder.getId());
					groupOrderPrice.setCreatorId(userId);
					groupOrderPrice.setCreatorName(userName);
					groupOrderPrice.setCreateTime(System.currentTimeMillis());
					groupOrderPrice.setTotalPrice(groupOrderPrice.getNumPerson()*groupOrderPrice.getNumTimes()*groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper.insert(groupOrderPrice);
				} else {
					groupOrderPrice.setTotalPrice(groupOrderPrice.getNumPerson()*groupOrderPrice.getNumTimes()*groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper
							.updateByPrimaryKeySelective(groupOrderPrice);

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
				groupOrderPriceMapper.deleteByPrimaryKey(groupOrderPrice
						.getId());
			}
		}

		// 收入

		List<GroupOrderPrice> incomeList = fitOrderVO.getGroupOrderPriceList();
		List<GroupOrderPrice> iList = groupOrderPriceMapper
				.selectByOrderAndType(groupOrder.getId(), 0);
		if (incomeList != null && incomeList.size() > 0) {
			for (GroupOrderPrice groupOrderPrice : incomeList) {
				if (groupOrderPrice.getId() == null) {
					groupOrderPrice.setOrderId(groupOrder.getId());
					groupOrderPrice.setCreatorId(userId);
					groupOrderPrice.setCreatorName(userName);
					groupOrderPrice.setCreateTime(System.currentTimeMillis());
					groupOrderPrice.setTotalPrice(groupOrderPrice.getNumPerson()*groupOrderPrice.getNumTimes()*groupOrderPrice.getUnitPrice());
					groupOrderPriceMapper.insert(groupOrderPrice);
				} else {
					groupOrderPrice.setTotalPrice(groupOrderPrice.getNumPerson()*groupOrderPrice.getNumTimes()*groupOrderPrice.getUnitPrice());
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
		groupOrderService.updateOrderAndGroupPrice(groupOrder.getId());
		
		
		
		
		// 接送信息

		List<GroupOrderTransport> transportList = fitOrderVO
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

		List<GroupOrderGuest> guestList = fitOrderVO.getGroupOrderGuestList();
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
				.selectByOrderId(groupOrder.getId());
		List<GroupRouteDayVO> groupRouteDayVOList = fitOrderVO
				.getGroupRouteDayVOList();
		if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {

				// 主表
				GroupRoute groupRoute = groupRouteDayVO.getGroupRoute();

				groupRoute.setOrderId(groupOrder.getId());
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

				// 附件
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
		
		if (mergeGroup &&  "add".equals(type)) { 
			
				if (groupOrder.getType()!=null && groupOrder.getType()==0) {
					return groupOrder.getId();
				}
				List<TourGroup> groupList = tourGroupMapper
						.selecGroupBefAutoMergerGroup(bizId,
								groupOrder.getDepartureDate(),
								groupOrder.getProductId());
				TourGroup tourGroup = null;
				// 如果存在加入团
				if (groupList != null && groupList.size() > 0) {
					tourGroup = groupList.get(0);
					tourGroup.setOrderNum(tourGroup.getOrderNum() + 1);
					tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
				} else { // 如果不存在新建团并加入
					tourGroup = new TourGroup();
					GroupRouteVO groupRouteVO = groupRouteService
							.findGroupRouteByOrderId(groupOrder.getId());
					groupRouteDayVOList = groupRouteVO.getGroupRouteDayVOList();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					tourGroup.setId(null);
					tourGroup.setBizId(bizId);
					tourGroup.setOrderNum(1);
					tourGroup.setGroupMode(0);
					tourGroup.setGroupState(1); // 默认已确认2015-12-08【欧】
					tourGroup.setPrudctBrandId(groupOrder.getProductBrandId());
					tourGroup.setProductBrandName(groupOrder
							.getProductBrandName());
					tourGroup.setProductId(groupOrder.getProductId());
					tourGroup.setProductName(groupOrder.getProductName());
					tourGroup.setOperatorId(proOperId==null?userId:proOperId);
					tourGroup.setOperatorName(proOperName==null?userName:proOperName);
					tourGroup.setDateStart(sdf.parse(groupOrder
							.getDepartureDate()));
					tourGroup.setTotalIncome(new BigDecimal(0));
					tourGroup.setCreateTime(System.currentTimeMillis());
					tourGroup.setDaynum(groupRouteDayVOList == null ? 0
							: groupRouteDayVOList.size());
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(groupOrder.getDepartureDate()));
					c.add(Calendar.DAY_OF_MONTH,
							groupRouteDayVOList == null ? 0
									: (groupRouteDayVOList.size() - 1));
					Date t = c.getTime();
					tourGroup.setDateEnd(t);
					
					TourGroup groupCodeSort = tourGroupMapper.selectGroupCodeSort(bizId,null,sdf.format(tourGroup.getDateStart()));
					tourGroup.setGroupCodeSort(groupCodeSort == null ? 1: groupCodeSort.getGroupCodeSort() + 1);
					
					if(fitOrderVO.isAgency()){
						tourGroup.setGroupCode(tourGroupService.makeCodeForAgency(bizCode,fitOrderVO.getProductCode(),sdf.format(tourGroup.getDateStart()), sdf.format(tourGroup.getDateEnd()),"", tourGroup.getGroupCodeSort()));
					}else{
						tourGroup.setGroupCode(tourGroupService.makeCodeByMode(bizCode, 0, groupOrder.getDepartureDate(), "", tourGroup.getGroupCodeSort()));
					}
					tourGroupMapper.insertSelective(tourGroup);

					if (groupRouteDayVOList != null
							&& groupRouteDayVOList.size() > 0) {
						for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {
							GroupRoute groupRoute = groupRouteDayVO
									.getGroupRoute();
							groupRoute.setId(null);
							groupRoute.setOrderId(null);
							groupRoute.setGroupId(tourGroup.getId());
							groupRoute
									.setCreateTime(System.currentTimeMillis());
							groupRouteMapper.insert(groupRoute);

							// 交通
							List<GroupRouteTraffic> groupRouteTrafficList = groupRouteDayVO
									.getGroupRouteTrafficList();
							if (groupRouteTrafficList != null
									&& groupRouteTrafficList.size() > 0) {
								for (GroupRouteTraffic groupRouteTraffic : groupRouteTrafficList) {
									groupRouteTraffic.setId(null);
									groupRouteTraffic.setRouteId(groupRoute
											.getId());
									groupRouteTraffic.setCreateTime(System
											.currentTimeMillis());
									groupRouteTrafficMapper
											.insert(groupRouteTraffic);

								}
							}
							// 备选商家
							List<GroupRouteSupplier> groupRouteOptionsSupplierList = groupRouteDayVO
									.getGroupRouteOptionsSupplierList();
							if (groupRouteOptionsSupplierList != null
									&& groupRouteOptionsSupplierList.size() > 0) {
								for (GroupRouteSupplier groupRouteSupplier : groupRouteOptionsSupplierList) {
									groupRouteSupplier.setId(null);
									groupRouteSupplier.setCreateTime(System
											.currentTimeMillis());
									groupRouteSupplier.setRouteId(groupRoute
											.getId());
									groupRouteSupplierMapper
											.insert(groupRouteSupplier);
								}

							}
							List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteDayVO
									.getGroupRouteAttachmentList();
							if (groupRouteAttachmentList != null
									&& groupRouteAttachmentList.size() > 0) {
								for (GroupRouteAttachment groupRouteAttachment : groupRouteAttachmentList) {
									groupRouteAttachment.setId(null);
									groupRouteAttachment.setCreateTime(System
											.currentTimeMillis());
									groupRouteAttachment.setObjId(groupRoute
											.getId());
									groupRouteAttachmentMapper
											.insert(groupRouteAttachment);
								}

							}
						}

					}

				}
				groupOrder.setGroupId(tourGroup.getId());
				groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
				groupOrderService.updateOrderAndGroupPrice(groupOrder.getId());
				groupOrderService.updateGroupPersonNum(tourGroup.getId());
			}
		
		
		return groupOrder.getId();

	}

	@Override
	public FitOrderVO selectFitOrderVOById(Integer orderId) {
		// TODO Auto-generated method stub
		FitOrderVO vo = new FitOrderVO();
		GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(orderId);
		TourGroup tg = tourGroupMapper.selectByPrimaryKey(groupOrder.getGroupId());
		if (tg != null)
			groupOrder.setGroupCode(tg.getGroupCode());
		vo.setGroupOrder(groupOrder);

		
		List<GroupRequirement> hotelList = groupRequirementMapper
				.selectByOrderAndType(orderId, 3);
		if (hotelList != null && hotelList.size() > 0) {
			vo.setHotelInfo(hotelList.get(0));
		}

		// 接送信息
		List<GroupOrderTransport> transList = groupOrderTransportMapper
				.selectByOrderId(orderId);
		vo.setGroupOrderTransportList(transList);

		// 成本
		List<GroupOrderPrice> outList = groupOrderPriceMapper
				.selectByOrderAndType(orderId, 1);
		vo.setGroupOrderCostList(outList);
		// 收入
		List<GroupOrderPrice> inList = groupOrderPriceMapper
				.selectByOrderAndType(orderId, 0);
		vo.setGroupOrderPriceList(inList);
		// 客人
		List<Integer> guestIdList = airTicketRequestService
				.findIssuedGuestIdList(groupOrder.getBizId(), orderId);
		List<GroupOrderGuest> guestList = groupOrderGuestMapper
				.selectByOrderId(orderId);
		if (guestList != null && guestList.size() > 0) {
			for (GroupOrderGuest groupOrderGuest : guestList) {
				groupOrderGuest.setEditType(!guestIdList.contains(groupOrderGuest.getId()));
			}
		}
		vo.setGroupOrderGuestList(guestList);
		// 行程
		return vo;
	}

	@Override
	public void mergetGroup(List<MergeGroupOrderVO> list, Integer bizId,
			Integer operid, String operName, String supplierCode,boolean isAgency)
			throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (MergeGroupOrderVO mergeGroupOrderVO : list) {
			GroupRouteVO groupRouteVO = new GroupRouteVO();
			List<GroupOrder> orderList = mergeGroupOrderVO.getOrderList();
			Integer orderId = null;
			Integer dayNum = null;
			// 设置订单
			groupRouteVO.setOrderList(orderList);

			for (GroupOrder groupOrder : orderList) {
				List<GroupRoute> rouList = groupRouteMapper
						.selectByOrderId(groupOrder.getId());
				if (rouList != null && rouList.size() > 0) {

					if (orderId == null) {
						orderId = groupOrder.getId();
					}
					if (dayNum == null) {
						dayNum = rouList.size();
					}
					if (rouList.size() > dayNum) {
						dayNum = rouList.size();
						orderId = groupOrder.getId();
					}
				}
			}
			GroupOrder go = groupOrderMapper.selectByPrimaryKey(orderId);

			List<GroupRouteDayVO> groupRouteDayVOList = new ArrayList<GroupRouteDayVO>();
			List<GroupRoute> routeList = groupRouteMapper
					.selectByOrderId(orderId);
			if (routeList != null) {
				for (GroupRoute groupRoute : routeList) {
					GroupRouteDayVO groupRouteDayVO = new GroupRouteDayVO();
					// 行程

					groupRouteDayVO.setGroupRoute(groupRoute);
					// 交通
					List<GroupRouteTraffic> trafficList = groupRouteTrafficMapper
							.selectGroupRouteTrafficByRouteId(groupRoute
									.getId());
					groupRouteDayVO.setGroupRouteTrafficList(trafficList);
					// 备选商家
					List<GroupRouteSupplier> supplierList = groupRouteSupplierMapper
							.selectGroupRouteSupplierByRouteId(groupRoute
									.getId());
					groupRouteDayVO
							.setGroupRouteOptionsSupplierList(supplierList);

					// 图片

					List<GroupRouteAttachment> attachmentList = groupRouteAttachmentMapper
							.selectByRouteId(groupRoute.getId());
					groupRouteDayVO.setGroupRouteAttachmentList(attachmentList);

					groupRouteDayVOList.add(groupRouteDayVO);
				}
			}
			// 设置行程
			groupRouteVO.setGroupRouteDayVOList(groupRouteDayVOList);
			// 设置团
			TourGroup tourGroup = new TourGroup();
			tourGroup.setCreateTime(System.currentTimeMillis());
			tourGroup.setGroupMode(0);
			tourGroup.setGroupState(1); // 默认已确认2015-12-08【欧】
			tourGroup.setBizId(bizId);
			tourGroup.setOperatorId(operid);
			tourGroup.setOperatorName(operName);
			tourGroup.setOrderNum(orderList.size());
			tourGroup.setProductId(go.getProductId());
			tourGroup.setProductName(go.getProductName());
			tourGroup.setPrudctBrandId(go.getProductBrandId());
			tourGroup.setProductBrandName(go.getProductBrandName());
			tourGroup.setDateStart(sdf.parse(go.getDepartureDate()));
			Date startTime = tourGroup.getDateStart();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			cal.add(Calendar.DAY_OF_MONTH, +groupRouteDayVOList.size() - 1);
			Date time = cal.getTime();
			tourGroup.setDateEnd(time);
			
			if(isAgency){  //组团社
				TourGroup selectGroupCodeSort = tourGroupMapper.selectGroupCodeSort(bizId,null, go.getDepartureDate());
				String makeCodeByMode =tourGroupService.makeCodeForAgency(supplierCode, mergeGroupOrderVO.getProductCode(), sdf.format(tourGroup.getDateStart()), sdf.format(tourGroup.getDateEnd()), "", selectGroupCodeSort == null ? 1 : selectGroupCodeSort
								.getGroupCodeSort() + 1);
				
				tourGroup.setGroupCode(makeCodeByMode);
				tourGroup.setGroupCodeSort(selectGroupCodeSort == null ? 1: selectGroupCodeSort.getGroupCodeSort() + 1);
				
			}else{ //地接社
				TourGroup selectGroupCodeSort = tourGroupMapper.selectGroupCodeSort(bizId,0, go.getDepartureDate());
				String makeCodeByMode = tourGroupService.makeCodeByMode(supplierCode,0,go.getDepartureDate(),null,selectGroupCodeSort == null ? 1 : selectGroupCodeSort.getGroupCodeSort() + 1);
				tourGroup.setGroupCode(makeCodeByMode);
				tourGroup.setGroupCodeSort(selectGroupCodeSort == null ? 1 : selectGroupCodeSort.getGroupCodeSort() + 1);
			}
			
			
			
			tourGroup.setDaynum(groupRouteDayVOList == null ? 0
					: groupRouteDayVOList.size());
			tourGroup.setServiceStandard(go.getServiceStandard());
			tourGroup.setRemark(go.getRemark());
			tourGroup.setRemarkInternal(go.getReceiveMode());
			groupRouteVO.setTourGroup(tourGroup);
			saveGroupRouteVO(groupRouteVO);
		}
		
	}

	void saveGroupRouteVO(GroupRouteVO groupRouteVO) {
		TourGroup tourGroup = groupRouteVO.getTourGroup();
		tourGroupMapper.insert(tourGroup);

		List<GroupOrder> orderList = groupRouteVO.getOrderList();
		List<Integer> idList = new ArrayList<Integer>();
		if (orderList != null) {
			for (GroupOrder groupOrder : orderList) {
				groupOrder.setGroupId(tourGroup.getId());
				groupOrder.setOperatorId(tourGroup.getOperatorId());
				groupOrder.setOperatorName(tourGroup.getOperatorName());
				groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
				List<GroupRequirement> list2 = groupRequirementMapper
						.selectByOrderId(groupOrder.getId());
				if (list2 != null && list2.size() > 0) {
					for (GroupRequirement groupRequirement : list2) {
						groupRequirement.setGroupId(tourGroup.getId());
						groupRequirementMapper
								.updateByPrimaryKeySelective(groupRequirement);
					}
				}
				idList.add(groupOrder.getId());
			}
		}
		bookingSupplierService.updateOperationAfterMergeGroupOrder(
				tourGroup.getId(), idList);
		groupOrderMapper.updateTourGroupPersonNum(tourGroup.getId());
		// groupOrderMapper.updateGroupPrice(tourGroup.getId());
		financeService.calcTourGroupAmount(tourGroup.getId());

		List<GroupRouteDayVO> groupRouteDayVOList = groupRouteVO
				.getGroupRouteDayVOList();
		if (groupRouteDayVOList != null && groupRouteDayVOList.size() > 0) {
			for (GroupRouteDayVO groupRouteDayVO : groupRouteDayVOList) {
				GroupRoute groupRoute = groupRouteDayVO.getGroupRoute();
				groupRoute.setId(null);
				groupRoute.setOrderId(null);
				groupRoute.setGroupId(tourGroup.getId());
				groupRoute.setCreateTime(System.currentTimeMillis());
				groupRouteMapper.insert(groupRoute);

				// 交通
				List<GroupRouteTraffic> groupRouteTrafficList = groupRouteDayVO
						.getGroupRouteTrafficList();
				if (groupRouteTrafficList != null
						&& groupRouteTrafficList.size() > 0) {
					for (GroupRouteTraffic groupRouteTraffic : groupRouteTrafficList) {
						groupRouteTraffic.setId(null);
						groupRouteTraffic.setRouteId(groupRoute.getId());
						groupRouteTraffic.setCreateTime(System
								.currentTimeMillis());
						groupRouteTrafficMapper.insert(groupRouteTraffic);

					}
				}
				// 备选商家
				List<GroupRouteSupplier> groupRouteOptionsSupplierList = groupRouteDayVO
						.getGroupRouteOptionsSupplierList();
				if (groupRouteOptionsSupplierList != null
						&& groupRouteOptionsSupplierList.size() > 0) {
					for (GroupRouteSupplier groupRouteSupplier : groupRouteOptionsSupplierList) {
						groupRouteSupplier.setId(null);
						groupRouteSupplier.setCreateTime(System
								.currentTimeMillis());
						groupRouteSupplier.setRouteId(groupRoute.getId());
						groupRouteSupplierMapper.insert(groupRouteSupplier);
					}

				}
				List<GroupRouteAttachment> groupRouteAttachmentList = groupRouteDayVO
						.getGroupRouteAttachmentList();
				if (groupRouteAttachmentList != null
						&& groupRouteAttachmentList.size() > 0) {
					for (GroupRouteAttachment groupRouteAttachment : groupRouteAttachmentList) {
						groupRouteAttachment.setId(null);
						groupRouteAttachment.setCreateTime(System
								.currentTimeMillis());
						groupRouteAttachment.setObjId(groupRoute.getId());
						groupRouteAttachmentMapper.insert(groupRouteAttachment);
					}

				}
			}

		}

	}

	@Override
	public List<GroupOrder> selectReserveOrderList() {
		// TODO Auto-generated method stub
		return groupOrderMapper.selectReserveOrderList();
	}
	
	/**
	 * 保存接送信息
	 * @param fitOrderVO
	 * @param userId
	 * @param userName
	 * @param proOperId
	 * @param proOperName
	 * @param bizId
	 * @param bizCode
	 * @param mergeGroup
	 * @return
	 * @throws ParseException
	 */
	@Transactional
	@Override
	public void saveTransportInfo(FitOrderVO fitOrderVO){
		
		GroupOrder groupOrder = fitOrderVO.getGroupOrder();
		List<GroupOrderTransport> transportList = fitOrderVO
				.getGroupOrderTransportList();
		List<GroupOrderTransport> transList = groupOrderTransportMapper.selectByOrderId(groupOrder.getId());
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
				groupOrderTransportMapper.deleteByPrimaryKey(groupOrderTransport.getId());
			}
		}
	}
	
	/**
	 * 保存客人名单
	 * @param fitOrderVO
	 * @param userId
	 * @param userName
	 * @param proOperId
	 * @param proOperName
	 * @param bizId
	 * @param bizCode
	 * @param mergeGroup
	 * @return
	 * @throws ParseException
	 */
	@Transactional
	@Override
	public void saveGuestInfo(FitOrderVO fitOrderVO){
		
		GroupOrder groupOrder = fitOrderVO.getGroupOrder();
		List<GroupOrderGuest> guestList = fitOrderVO.getGroupOrderGuestList();
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
	}

}

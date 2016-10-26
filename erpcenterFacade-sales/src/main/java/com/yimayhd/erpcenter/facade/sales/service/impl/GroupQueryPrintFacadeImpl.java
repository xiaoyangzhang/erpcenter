package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupPriceVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.query.ToSKConfirmPreviewDTO;
import com.yimayhd.erpcenter.facade.sales.result.ToSKConfirmPreviewResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupQueryPrintFacade;
import com.yimayhd.erpcenter.facade.sales.utils.DateUtils;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class GroupQueryPrintFacadeImpl implements GroupQueryPrintFacade{

	@Autowired
	private TourGroupBiz tourGroupService;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;

	@Autowired
	private PlatformOrgBiz orgService;

	@Autowired
	private GroupOrderBiz groupOrderService;

	@Autowired
	private SupplierBiz supplierService;
	
	@Autowired
	private GroupRouteBiz groupRouteService;
	
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestService;
	
	@Autowired
	private GroupRequirementBiz groupRequirementService;
	
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportService;
	
	@Autowired
	private BookingGuideBiz bookingGuideService;
	
//	@Autowired
//	private DicBiz dicService;
	
	@Override
	public ToSKConfirmPreviewResult toSKConfirmPreview(ToSKConfirmPreviewDTO toSKConfirmPreviewDTO) {
		
		Integer groupId=toSKConfirmPreviewDTO.getGroupId();
		Integer curUserId=toSKConfirmPreviewDTO.getCurUserId();
		Integer supplierId=toSKConfirmPreviewDTO.getSupplierId();
		Integer curBizId=toSKConfirmPreviewDTO.getCurBizId();
		
		
		//FIXME 这个在web中做了单独的封装
		String imgPath = null;
		
		TourGroup tour = tourGroupService.selectByPrimaryKey(groupId);
		PlatformEmployeePo po = platformEmployeeService.findByEmployeeId(curUserId);
		po.setOrgName(orgService.findByOrgId(po.getOrgId()).getName());
		List<GroupOrder> suppliers = groupOrderService.selectSupplierByGroupId(groupId);
		SupplierInfo supplierInfo = null;
		List<SupplierInfo> supplierList = new ArrayList<SupplierInfo>();

		for (GroupOrder order : suppliers) {
			supplierInfo = supplierService.selectBySupplierId(order.getSupplierId());
			supplierList.add(supplierInfo);
		}
		
		// 行程
		GroupOrder supplier = null;
		if (null == supplierId && supplierList.size() > 0) {
			supplierId = supplierList.get(0).getId();
		}
		GroupRouteVO vo = groupRouteService.findGroupRouteByGroupIdAndSupplierId(groupId, supplierId);

		List<GroupRouteDayVO> groupRouteDayVOs = vo.getGroupRouteDayVOList();

		List<GroupPriceVo> vos = groupOrderService.selectSupplierByGroupIdAndSupplierId(groupId, supplierId);

		List<GroupOrder> orders = groupOrderService.selectOrderByGroupIdAndBizIdAndSupplierId(groupId, supplierId,curBizId);
		for (GroupOrder groupOrder : orders) {
			if (groupOrder.getSupplierId() == supplierId) {
				supplier = groupOrder;
			}
		}
		GroupOrderPrintPo gopp = null;
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		for (GroupOrder order : orders) {
			List<GroupOrderGuest> guests = groupOrderGuestService.selectByOrderId(order.getId());
			gopp = new GroupOrderPrintPo();
			// 客人接送信息
			gopp.setSupplierName(order.getSupplierName());
			gopp.setSaleOperatorName(order.getSaleOperatorName());
			gopp.setRemark(order.getRemarkInternal());
			gopp.setPlace((order.getProvinceName() == null ? "" : order.getProvinceName()) + (order.getCityName() == null ? "" : order.getCityName()));
			gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
			// 根据散客订单统计客人信息
			if (guests.size() > 0) {
				gopp.setCertificateNums(getGuestInfoNoPhone(guests));
				for (GroupOrderGuest guest : guests) {
					if (guest.getIsLeader() == 1) {
						gopp.setGuesStatic(guest.getName() + "\n"+ guest.getMobile());
						break;
					}
				}
				/*
				if (gopp.getGuestInfo() == null || gopp.getGuestInfo() == "") {
					// 如果客人中没有领队，默认取一条数据显示
					gopp.setGuesStatic(guests.get(0).getName() + "\n"
							+ guests.get(0).getMobile());
				}*/
			}
			
			gopp.setReceiveMode(order.getReceiveMode());
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService.selectByOrderAndType(order.getId(), 3);
			if (grogShopList.size() > 0) {
				if (grogShopList.get(0).getHotelLevel() != null) {
					//FIXME 这里dicService实现的有问题
					//gopp.setHotelLevel(dicService.getById(grogShopList.get(0).getHotelLevel()).getValue()+ "\n");
				}
				gopp.setHotelNum(getHotelNum(grogShopList));
			}
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService.selectByOrderId(order.getId());
			gopp.setAirPickup("接机：" + getAirInfo(groupOrderTransports, 0));
			
			// 根据散客订单统计送机信息
			gopp.setAirOff("送机：" + getAirInfo(groupOrderTransports, 1));

			// 省内交通
			gopp.setTrans("省内：" + getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		
		ToSKConfirmPreviewResult result=new ToSKConfirmPreviewResult();
		
		result.setGopps(gopps);
		result.setGroupRouteDayVOs(groupRouteDayVOs);
		result.setGuide(getGuides(guides));
		result.setGuides(guides);
		result.setImgPath(imgPath);
		result.setPo(po);
		result.setSupplier(supplier);
		result.setSupplierList(supplierList);
		result.setTour(tour);
		result.setVos(vos);
		
		return result;
	}
	
	/**
	 * 返回客人信息(不包含电话号码)
	 * 
	 * @param guests
	 * @return
	 */
	public String getGuestInfoNoPhone(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderGuest guest : guests) {
			sb.append(guest.getName() + " " + guest.getCertificateNum() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * 返回酒店信息（不包含星级）
	 * 
	 * @param grogShopList
	 * @return
	 */
	public String getHotelNum(List<GroupRequirement> grogShopList) {
		StringBuilder sb = new StringBuilder();
		if (grogShopList.size() > 0) {
			String sr = "";
			String dr = "";
			String tr = "";
			String eb = "";
			String pf = "";
			GroupRequirement gr = grogShopList.get(0);
			if (gr.getCountSingleRoom() != null && gr.getCountSingleRoom() != 0) {
				sr = gr.getCountSingleRoom() + "单间" + " ";
			}
			if (gr.getCountDoubleRoom() != null && gr.getCountDoubleRoom() != 0) {
				dr = gr.getCountDoubleRoom() + "标间" + " ";
			}
			if (gr.getCountTripleRoom() != null && gr.getCountTripleRoom() != 0) {
				tr = gr.getCountTripleRoom() + "三人间" + "";
			}
			if (gr.getExtraBed() != null && gr.getExtraBed() != 0) {
				eb = gr.getExtraBed() + "加床" + "";
			}
			if (gr.getPeiFang() != null && gr.getPeiFang() != 0) {
				pf = gr.getPeiFang() + "陪房";
			}
			sb.append(sr + dr + tr + eb + pf);
		}
		return sb.toString();
	}
	
	/**
	 * 组织所有导游信息
	 * 
	 * @param guides
	 * @return
	 */
	public String getGuides(List<BookingGuide> guides) {
		StringBuilder sb = new StringBuilder();
		for (BookingGuide guide : guides) {
			sb.append(guide.getGuideName() + " " + guide.getGuideMobile()
					+ "\n");
		}
		return sb.toString();
	}
	
	/**
	 * 接送信息
	 * 
	 * @param groupOrderTransports
	 * @param flag
	 *            0表示接信息 1表示送信息
	 * @return
	 */
	public String getAirInfo(List<GroupOrderTransport> groupOrderTransports,
			Integer flag) {
		StringBuilder sb = new StringBuilder();
		if (flag == 0) {
			for (GroupOrderTransport transport : groupOrderTransports) {
				if (transport.getType() == 0 && transport.getSourceType() == 1) {
					sb.append((transport.getDepartureCity() == null ? ""
							: transport.getDepartureCity())
							+ "/"
							+ (transport.getArrivalCity() == null ? ""
									: transport.getArrivalCity())
							+ " "
							+ (transport.getClassNo() == null ? "" : transport
									.getClassNo())
							+ " "
							+ " 发出时间："
							+ (DateUtils.format(transport.getDepartureDate(),
									"MM-dd") == null ? "" : DateUtils.format(
									transport.getDepartureDate(), "MM-dd"))
							+ " "
							+ (transport.getDepartureTime() == null ? ""
									: transport.getDepartureTime()) + "\n");
				}
			}
		}
		if (flag == 1) {
			for (GroupOrderTransport transport : groupOrderTransports) {
				if (transport.getType() == 1 && transport.getSourceType() == 1) {
					sb.append((transport.getDepartureCity() == null ? ""
							: transport.getDepartureCity())
							+ "/"
							+ (transport.getArrivalCity() == null ? ""
									: transport.getArrivalCity())
							+ " "
							+ (transport.getClassNo() == null ? "" : transport
									.getClassNo())
							+ " "
							+ " 发出时间："
							+ (DateUtils.format(transport.getDepartureDate(),
									"MM-dd") == null ? "" : DateUtils.format(
									transport.getDepartureDate(), "MM-dd"))
							+ " "
							+ (transport.getDepartureTime() == null ? ""
									: transport.getDepartureTime()) + "\n");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 省内交通
	 * 
	 * @param groupOrderTransports
	 * @param flag
	 *            0表示接信息 1表示送信息
	 * @return
	 */
	public String getSourceType(List<GroupOrderTransport> groupOrderTransports) {
		StringBuilder sb = new StringBuilder();
		for (GroupOrderTransport transport : groupOrderTransports) {
			if (transport.getSourceType() == 0) {
				sb.append((transport.getDepartureCity() == null ? ""
						: transport.getDepartureCity())
						+ "/"
						+ (transport.getArrivalCity() == null ? "" : transport
								.getArrivalCity())
						+ " "
						+ (transport.getClassNo() == null ? "" : transport
								.getClassNo())
						+ " "
						+ " 发出时间："
						+ (DateUtils.format(transport.getDepartureDate(),
								"MM-dd") == null ? "" : DateUtils.format(
								transport.getDepartureDate(), "MM-dd"))
						+ " "
						+ (transport.getDepartureTime() == null ? ""
								: transport.getDepartureTime()) + "\n");
			}
		}
		return sb.toString();
	}

}

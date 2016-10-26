package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
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
import com.yimayhd.erpcenter.facade.sales.result.PreviewFitTransferResult;
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
	
	@Autowired
	private DicBiz dicService;
	
	@Autowired
	private BookingDeliveryBiz deliveryService;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierService;
	
	@Autowired
	private BookingSupplierDetailBiz detailService;
	
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
	
	@Override
	public PreviewFitTransferResult previewFitTransfer(Integer groupId) {
		
		//FIXME 这个在web中做了单独的封装
		String imgPath = null;
	
		TourGroup tourGroup = tourGroupService.selectByPrimaryKey(groupId);
		List<BookingGuide> guides = bookingGuideService.selectGuidesByGroupId(groupId);
		String guideString = "";
		String driverString = "";
		if (guides.size() > 0) {
			guideString = getGuides(guides);
			driverString = getDrivers(guides);
		}
		//预定车信息
		List<BookingSupplier> bs4 = bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(groupId, 4) ;
		StringBuilder sbsb = new StringBuilder() ;
		for (BookingSupplier bs : bs4) {
			List<BookingSupplierDetail> details = detailService.selectByPrimaryBookId(bs.getId()) ;
			for (BookingSupplierDetail bsd : details) {
				sbsb.append(bsd.getDriverName()+" "+bsd.getDriverTel()+" "+bsd.getCarLisence()+"\n") ;
			}
		}
		driverString = sbsb.toString() ;
		
		List<GroupOrder> orders = groupOrderService.selectOrderByGroupId(groupId);
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
		GroupOrderPrintPo gopp = null;
		String total = getHotelTotalNum(orders);
		for (GroupOrder order : orders) {
			// 拿到单条订单信息
			gopp = new GroupOrderPrintPo();
			gopp.setSupplierName(order.getSupplierName()+"\n"+order.getContactName());
			gopp.setReceiveMode(order.getReceiveMode());
			
			gopp.setSaleOperatorName(order.getSaleOperatorName());
			gopp.setRemark(order.getRemarkInternal());
			gopp.setPlace((order.getProvinceName() == null ? "" : order
					.getProvinceName())
					+ (order.getCityName() == null ? "" : order.getCityName()));
			// 根据散客订单统计人数
			/*Integer numAdult = groupOrderGuestService
					.selectNumAdultByOrderID(order.getId());
			Integer numChild = groupOrderGuestService
					.selectNumChildByOrderID(order.getId());*/
			gopp.setPersonNum(order.getNumAdult()+"+"+order.getNumChild());
			// 根据散客订单统计客人信息
			List<GroupOrderGuest> guests = groupOrderGuestService
					.selectByOrderId(order.getId());
			/*for (GroupOrderGuest guest : guests) {
				if (guest.getIsLeader() == 1) {
					gopp.setGuesStatic(guest.getName() + " " + guests.size()
							+ "人" + "\n" + guest.getMobile());
					break;
				}
			}
			if (gopp.getGuesStatic() == null || gopp.getGuesStatic() == "") {
				// 如果客人中没有领队，默认取一条数据显示
				gopp.setGuesStatic(guests.get(0).getName() + "\n"
						+ guests.get(0).getMobile());
			}*/
			gopp.setGuesStatic(order.getReceiveMode());
			gopp.setGuestInfo(getGuestInfo2(guests));
			gopp.setGuests(guests);
			// 根据散客订单统计酒店信息
			List<GroupRequirement> grogShopList = groupRequirementService
					.selectByOrderAndType(order.getId(), 3);
			StringBuilder sb = new StringBuilder();
			for (GroupRequirement gsl : grogShopList) {
				if (gsl.getHotelLevel() != null) {
					//FIXME 待修复
					//sb.append(dicService.getById(gsl.getHotelLevel()).getValue() + "\n");
				}
			}
			gopp.setHotelLevel(sb.toString());
			gopp.setHotelNum(getHotelNum(grogShopList));
			// 省外交通
			// 根据散客订单统计接机信息
			List<GroupOrderTransport> groupOrderTransports = groupOrderTransportService
					.selectByOrderId(order.getId());
			gopp.setAirPickup(getAirInfo(groupOrderTransports, 0));
			// 根据散客订单统计送机信息
			gopp.setAirOff(getAirInfo(groupOrderTransports, 1));
			// 省内交通
			gopp.setTrans(getSourceType(groupOrderTransports));
			gopps.add(gopp);
		}

		List<BookingDelivery> bds = deliveryService.getDeliveryListByGroupId(groupId);
		String deliveryDetail = getDeliveryInfo(bds);
		
		
		PreviewFitTransferResult result=new PreviewFitTransferResult();
		
		result.setDeliveryDetail(deliveryDetail);
		result.setDriverString(driverString);
		result.setGopps(gopps);
		result.setGuideString(guideString);
		result.setImgPath(imgPath);
		result.setTotal(total);
		result.setTourGroup(tourGroup);
		
		return result;
	}
	
	/**
	 * 返回客人信息
	 * 格式如下：
	 * 	张三(13787654321)  530111198307276576   有手机号的显示方式
	 *  李四  530111198307276576				 没有手机号的显示形式
	 * @param guests
	 * @return
	 */
	public String getGuestInfo2(List<GroupOrderGuest> guests) {
		StringBuilder sb = new StringBuilder();
		if(guests == null || guests.size() == 0){
			return sb.toString();
		}
		
		GroupOrderGuest guest = null;
		for (int i = 0; i < guests.size(); i++) {
			if(i > 0){
				sb.append("\n");
			}
			guest = guests.get(i);
			sb.append(guest.getName());
			if(StringUtils.isNotEmpty(guest.getMobile())){
				sb.append("【"+ guest.getMobile() +"】  ");
			}
			sb.append(guest.getCertificateNum());
			
		}
		return sb.toString();
	}
	
	/**
	 * 统计所有订单酒店总房间数
	 * 
	 * @param grogShopList
	 * @return
	 */
	public String getHotelTotalNum(List<GroupOrder> orders) {
		StringBuilder sb = new StringBuilder();
		Integer sr = 0;
		Integer dr = 0;
		Integer tr = 0;
		Integer eb = 0;
		Integer pf = 0;
		for (GroupOrder order : orders) {
			List<GroupRequirement> grogShopList = groupRequirementService
					.selectByOrderAndType(order.getId(), 3);
			for (GroupRequirement gr : grogShopList) {
				if (gr.getCountSingleRoom() != null
						&& gr.getCountSingleRoom() != 0) {
					sr += gr.getCountSingleRoom();
				}
				if (gr.getCountDoubleRoom() != null
						&& gr.getCountDoubleRoom() != 0) {
					dr += gr.getCountDoubleRoom();
				}
				if (gr.getCountTripleRoom() != null
						&& gr.getCountTripleRoom() != 0) {
					tr += gr.getCountTripleRoom();
				}
				if (gr.getExtraBed() != null && gr.getExtraBed() != 0) {
					eb += gr.getExtraBed();
				}
				if (gr.getPeiFang() != null && gr.getPeiFang() != 0) {
					pf += gr.getPeiFang();
				}
			}
		}

		if (sr != 0) {
			sb.append(sr + "单 ");
		}
		if (dr != 0) {
			sb.append(dr + "标 ");
		}
		if (tr != 0) {
			sb.append(tr + "三人间 ");
		}
		if (eb != 0) {
			sb.append(eb + "加床 ");
		}
		if (pf != 0) {
			sb.append(pf + "陪房 ");
		}
		return sb.toString();
	}
	
	
	/**
	 * 组织所有司机信息
	 */
	public String getDrivers(List<BookingGuide> guides) {
		StringBuilder sb = new StringBuilder();
		for (BookingGuide guide : guides) {
			BookingSupplierDetail bsd = detailService.selectByPrimaryKey(guide.getBookingDetailId());
			if (bsd != null) {
				sb.append(bsd.getDriverName() + " " + bsd.getDriverTel() + "\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 返回地接社信息
	 */
	public String getDeliveryInfo(List<BookingDelivery> bds) {
		StringBuilder sb = new StringBuilder();
		for (BookingDelivery bd : bds) {
			sb.append(bd.getSupplierName() + " " + bd.getContact() + " "
					+ bd.getContactMobile() + " " + "Tel:" + bd.getContactTel()
					+ " " + "Fax:" + bd.getContactFax() + "\n");
		}
		return sb.toString();
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

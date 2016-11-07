package com.yimayhd.erpcenter.facade.operation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuideListCount;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupGuidePrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.operation.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.operation.result.BookingGuideResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;
import com.yimayhd.erpcenter.facade.operation.service.BookingGuideFinanceFacade;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierGuide;

public class BookingGuideFinanceFacadeImpl implements BookingGuideFinanceFacade {

	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private BookingSupplierDetailBiz bookinggSupplierDetailBiz;
	@Autowired
	private FinanceGuideBiz financeGuideBiz;
	@Autowired
	private SupplierGuideBiz supplierGuideBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	@Autowired
	private BookingShopBiz bookingShopBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private DicBiz dicBiz;
	@Override
	public PageBean getGuideGroupList2(PageBean pageBean, TourGroupVO group,
			Set<Integer> set) {
		 PageBean bean = tourGroupBiz.getGuideGroupList2(pageBean, group, set);
		return bean;
	}
	@Override
	public ResultSupport changeStateLock(Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		bookingGuideBiz.updateStateLock(groupId);
		return resultSupport;
	}
	@Override
	public ResultSupport changeStateUnlock(Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		bookingGuideBiz.updateStateUnlock(groupId);
		return resultSupport;
	}
	@Override
	public BookingGuideResult guideDetailList(Integer groupId) {
		BookingGuideResult result = new BookingGuideResult();
		TourGroup tg = tourGroupBiz.selectByPrimaryKey(groupId) ;
		result.setTourGroup(tg);
		//查询需求订单
		List<GroupRequirement> groupRequirements = groupRequirementBiz.selectByGroupIdAndType(groupId, Constants.GUIDE);
		if (groupRequirements!=null && groupRequirements.size()>0) {
			GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(groupRequirements.get(0).getOrderId());
			for (GroupRequirement req : groupRequirements) {
				if (groupOrder!=null) {
					req.setNameFull(groupOrder.getSupplierName());
				}
			}
		}
		result.setGroupRequirements(groupRequirements);
		List<BookingGuidesVO> vo = bookingGuideBiz.selectBookingGuideVoByGroupId(groupId);
		result.setBookingGuidesVOs(vo);
		return result;
	}
	@Override
	public List<BookingGuidesVO> guideDetailListView(Integer groupId) {
		List<BookingGuidesVO> vo = bookingGuideBiz.selectBookingGuideVoByGroupId(groupId);
		return vo;
	}
	@Override
	public WebResult<Map<Object, Object>> saveGuide(BookingGuidesVO guidesVO, String bizCode) {
		WebResult<Map<Object, Object>> result = new WebResult<Map<Object, Object>>();
		BookingGuide guide = guidesVO.getGuide();
		if(guide.getId()==null){
			int No = bookingGuideBiz.getBookingCountByTime();
			guide.setBookingNo(bizCode+Constants.GUIDE+new SimpleDateFormat("yyMMdd").format(new Date())+(No+100));
		}
		int insertResult = bookingGuideBiz.insertSelective(guidesVO);
		//>0?successJson("id",guide.getGroupId()+""):errorJson("操作失败！");
		Map<Object, Object> map = new HashMap<Object, Object>();
		if(insertResult < 1) {
			result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}else {
			map.put("id", guide.getGroupId());
			result.setValue(map);
			
		}
		return result;
	}
	@Override
	public BookingGuideResult toEditGuideView(Integer groupId,
			Integer bookingGuideId) {
		BookingGuideResult result = new BookingGuideResult();
		if(bookingGuideId != null){
			BookingGuidesVO vo = bookingGuideBiz.selectBookingGuideVoByGroupIdAndId(bookingGuideId);
			result.setGuidesVO(vo);
		}
		List<BookingSupplierDetail> driverList = bookinggSupplierDetailBiz.getDriversByGroupIdAndType(groupId,Constants.FLEET);
		result.setSupplierDetails(driverList);
		return result;
	}
	@Override
	public ResultSupport financeSave(List<FinanceGuide> financeGuides) {
		ResultSupport resultSupport = new ResultSupport();
		BookingGuide guide = bookingGuideBiz.selectByPrimaryKey(financeGuides.get(0).getBookingId());
		if(guide.getStateFinance()!=null && guide.getStateFinance().equals(1)){
//			return errorJson("已审核！");
			resultSupport.setErrorCode(OperationErrorCode.CHECKED);
			return resultSupport;
		}
		try{
			financeGuideBiz.financeBatchSave(financeGuides);
		}catch(Exception ex){
//			return errorJson("操作失败！");
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
//			return resultSupport;
		}
		return resultSupport;
	}
	@Override
	public PageBean bookingGuideList(PageBean pageBean, Integer bizId,
			Set<Integer> set) {
		PageBean page = bookingGuideBiz.selectBookingGuideListCountListPage(pageBean, bizId,set);
		List<BookingGuideListCount> resultList = pageBean.getResult();
		if(resultList!=null&&resultList.size()>0){
			for (BookingGuideListCount bookingGuideListCount : resultList) {
				SupplierGuide guideInfo = supplierGuideBiz.getGuideInfoById(bookingGuideListCount.getGuideId());
				if(guideInfo!=null){
					bookingGuideListCount.setGuideNo(guideInfo.getLicenseNo());
				}
			}
		}
		return page;
	}
	@Override
	public BookingGuideResult createGroupOrder(Integer guideId, Integer num) {
		BookingGuideResult result = new BookingGuideResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
		//查询导游信息
				BookingGuide bookingGuide = bookingGuideBiz.selectByPrimaryKey(guideId);
				result.setBookingGuide(bookingGuide);
				//团信息
				TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(bookingGuide.getGroupId());
				result.setTourGroup(tourGroup);
				/**
				 * 获取全陪，定制团一个团对应一个订单
				 */
//				String accompanys = "" ;
				List<GroupOrder> orders = groupOrderBiz.selectOrderByGroupId(tourGroup.getId()) ;
				GroupOrder order = orders.get(0) ;
				List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(order.getId()) ;
//				for (GroupOrderGuest guest : guests) { 
//					if(guest.getType()==3){
//						accompanys = guest.getName()+"-"+guest.getMobile() ;
//						break ;
//					}
//				}
				result.setGuestGuides(guests);
				/**
				 * 行程列表
				 */
				List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(bookingGuide.getGroupId());
				result.setGroupRoutes(routeList);
				
				/**
				 * 计调信息
				 */
				List<GroupGuidePrintPo> pos = new ArrayList<GroupGuidePrintPo>() ;
				GroupGuidePrintPo po = null ;
				//预定下接社信息
				List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
				List<BookingDelivery> deliveries = bookingDeliveryBiz.getDeliveryListByGroupId(tourGroup.getId()) ;
				result.setBookingDeliveries(deliveries);
				//预定购物
				List<BookingShop> shops = bookingShopBiz.getShopListByGroupId(tourGroup.getId()) ;
				result.setBookingShops(shops);
				/**
				 * 预订房信息
				 */
				List<BookingSupplier> bs3 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(), 3) ;
				for (BookingSupplier bs : bs3) {
					po = new  GroupGuidePrintPo();
					po.setSupplierType("房");
					po.setSupplierName(bs.getSupplierName());
					po.setContacktWay(bs.getContact()+"-"+bs.getContactMobile());
					po.setPaymentWay(bs.getCashType());
					List<BookingSupplierDetail> details = bookinggSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
					StringBuilder sb = new StringBuilder() ;
					for (BookingSupplierDetail bsd : details) {
						String dd = "" ;
						if(bsd.getItemDate()!=null){
							dd = sdf.format(bsd.getItemDate()) ;
						}
						sb.append(dd+
								" 【"+bsd.getType1Name()+"】 "+
								bsd.getItemPrice().toString().replace(".0","")+
								"*("+bsd.getItemNum().toString().replace(".0","")+
								"-"+bsd.getItemNumMinus().toString().replace(".0","")+")");
					}
					po.setDetail(sb.toString());
					
					pos.add(po) ;
				}
				/**
				 * 预定车信息
				 */
				List<BookingSupplier> bs4 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(), 4) ;
				for (BookingSupplier bs : bs4) {
					po = new  GroupGuidePrintPo();
					po.setSupplierType("车");
					po.setSupplierName(bs.getSupplierName());
					po.setContacktWay(bs.getContact()+"-"+bs.getContactMobile());
					po.setPaymentWay(bs.getCashType());
					List<BookingSupplierDetail> details = bookinggSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
					StringBuilder sb = new StringBuilder() ;
					for (BookingSupplierDetail bsd : details) {
						String dd = "" ;
						if(bsd.getItemDate()!=null){
							dd = sdf.format(bsd.getItemDate()) ;
						}
						sb.append(dd+
								" 【"+bsd.getType1Name()+"】 "+
								bsd.getItemPrice().toString().replace(".0","")+
								"*("+bsd.getItemNum().toString().replace(".0","")+
								"-"+(bsd.getItemNumMinus()==null?0:bsd.getItemNumMinus().toString().replace(".0",""))+")");
					}
					po.setDetail(sb.toString());
					
					pos.add(po) ;
				}
				/**
				 * 预定景区信息
				 */
				List<BookingSupplier> bs5 = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(tourGroup.getId(), 5) ;
				for (BookingSupplier bs : bs5) {
					po = new  GroupGuidePrintPo();
					po.setSupplierType("景区");
					po.setSupplierName(bs.getSupplierName());
					po.setContacktWay(bs.getContact()+"-"+bs.getContactMobile());
					po.setPaymentWay(bs.getCashType());
					List<BookingSupplierDetail> details = bookinggSupplierDetailBiz.selectByPrimaryBookId(bs.getId()) ;
					StringBuilder sb = new StringBuilder() ;
					for (BookingSupplierDetail bsd : details) {
						String dd = "" ;
						if(bsd.getItemDate()!=null){
							dd = sdf.format(bsd.getItemDate()) ;
						}
						sb.append(dd+
								" 【"+bsd.getType1Name()+"】 "+
								bsd.getItemPrice().toString().replace(".0","")+
								"*("+bsd.getItemNum().toString().replace(".0","")+
								"-"+bsd.getItemNumMinus().toString().replace(".0","")+")");
					}
					po.setDetail(sb.toString());
					
					pos.add(po) ;
				}
				result.setGroupGuides(pos);
				if(num==2){
					List<GroupOrder> orders1 = groupOrderBiz.selectOrderByGroupId(tourGroup.getId()) ;
					List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>() ;
					GroupOrderPrintPo gopp = null ;
					for (GroupOrder order1 : orders1) {
						//拿到单条订单信息
						gopp = new GroupOrderPrintPo() ;
						gopp.setRemark(order.getRemark());
						
						//根据散客订单统计人数
						Integer numAdult = groupOrderGuestBiz.selectNumAdultByOrderID(order1.getId()) ;
						Integer numChild = groupOrderGuestBiz.selectNumChildByOrderID(order1.getId()) ;
						gopp.setPersonNum(numAdult+"大"+numChild+"小");
						//根据散客订单统计客人信息
						List<GroupOrderGuest> guests1 = groupOrderGuestBiz.selectByOrderId(order1.getId()) ;
						gopp.setGuests(guests1);
						//根据散客订单统计酒店信息
						List<GroupRequirement> grogShopList = groupRequirementBiz
								.selectByOrderAndType(order1.getId(), 3);
						StringBuilder sb = new StringBuilder();
						for (GroupRequirement groupRequirement : grogShopList) {
							sb.append(groupRequirement.getRequireDate() + " "
									+ dicBiz.getById(Integer.parseInt(groupRequirement.getHotelLevel())).getValue() + " "
									+ groupRequirement.getCountSingleRoom() + "单间" + " "
									+ groupRequirement.getCountDoubleRoom() + "标间" + " "
									+ groupRequirement.getCountTripleRoom() + "三人间");
						}
						gopp.setHotelInfo(sb.toString());
						//根据散客订单统计接机信息
						List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz
								.selectByOrderId(order1.getId());
						gopp.setOrderTransports(groupOrderTransports);
						//根据散客订单统计送机信息
						gopps.add(gopp) ;
					}
					
					
					result.setGroupOrderPrints(gopps);
				}
				
		return result;
	}

}

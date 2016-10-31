/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.operation;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBillBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierPO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.errorcode.SaleErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.BookingDeliveryQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpcenter.facade.sales.result.operation.BookingSupplierResult;
import com.yimayhd.erpcenter.facade.sales.service.BookingSupplierFacade;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierItemBiz;
import com.yimayhd.erpresource.dal.po.SupplierContract;
import com.yimayhd.erpresource.dal.po.SupplierContractPrice;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
import com.yimayhd.erpresource.dal.po.SupplierItem;

/**
 * @ClassName: BookingSupplierFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class BookingSupplierFacadeImpl implements BookingSupplierFacade {

	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private FinanceBillBiz financeBillBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private SupplierItemBiz supplierItemBiz;
	@Autowired
	private SupplierBiz supplierBiz;
	@Autowired
	private FinanceGuideBiz financeGuideBiz;
	@Autowired
	private ContractBiz contractBiz;
	@Autowired
	private DicBiz dicBiz;
//	@Autowired
//	private logb
	@Override
	public BookingSupplierResult getDeliveryExportInfo(BookingDeliveryQueryDTO dto) {
		BookingSupplierResult result  = new BookingSupplierResult();
		List<BookingSupplierDetail> driversByGroupIdAndType = bookingSupplierDetailBiz.getDriversByGroupIdAndType(dto.getGroupId(), Constants.FLEET);
		result.setDetailList(driversByGroupIdAndType);
		List<GroupRoute> groupRoutes = groupRouteBiz.selectByGroupIdAndBookingId(dto.getGroupId(),dto.getBookingId());
		result.setRouteList(groupRoutes);
		List<BookingGuide> bookingGuides = bookingGuideBiz.selectByGroupId2(dto.getGroupId());
		result.setbGuides(bookingGuides);
		 List<GroupOrder> orderList = null;
		if (dto.getGroupMode() > 0) {//团队
            orderList = groupOrderBiz.selectOrderByGroupId(dto.getGroupId());
        } else {//散客
            List<BookingDeliveryOrder> deliveryOrderList = dto.getOrderList();
            orderList = new ArrayList<GroupOrder>();
            if (deliveryOrderList != null && deliveryOrderList.size() > 0) {
                for (com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder deliveryOrder : deliveryOrderList) {
                    orderList.add(groupOrderBiz.selectByPrimaryKey(deliveryOrder.getOrderId()));
                }
            }
        }
		result.setOrderList(orderList);
		if (!CollectionUtils.isEmpty(orderList)) {
			
			List<GroupOrderGuest> groupOrderGuests = groupOrderGuestBiz.selectByOrderId(orderList.get(0).getId());
			result.setGuestList(groupOrderGuests);
		}
		return result;
	}
	@Override
	public PageBean selectreceiveOrderListSelectPage(PageBean page,
			Integer bizId, Set<Integer> set) {
		PageBean pageBean = financeBillBiz.selectreceiveOrderListSelectPage(page, bizId,"", set);

		return pageBean;
	}
	@Override
	public List<FinanceBillDetail> getbillListByIdAndGuideId(Integer bizId,
			String groupId, String guideId) {
		List<FinanceBillDetail> financeBillDetailList = financeBillBiz.getbillListByIdAndGuideId(bizId, groupId, guideId);

		return financeBillDetailList;
	}
	@Override
	public BookingSupplierResult applyPrint(Integer bizId, String groupId,
			String guideId) {
		BookingSupplierResult result = new BookingSupplierResult();
//		List<FinanceBillDetail> financeBillDetailList = financeBillBiz.getbillListByIdAndGuideId(WebUtils.getCurBizId(request), groupId, guideId);
		result.setFinanceBillDetails(getbillListByIdAndGuideId(bizId, groupId, guideId));
		TourGroup tour = tourGroupBiz.selectByPrimaryKey(Integer.parseInt(groupId));
		result.setTourGroup(tour);
		return result;
	}
	@Override
	public BookingSupplierResult groupHotelDetailPreview(Map<Object, Object> paramMap) {
		BookingSupplierResult result = new BookingSupplierResult();
		List<Map<String, Object>> groupHotelBookings = tourGroupBiz.getGroupHotelBooking(paramMap);
		result.setMapList(groupHotelBookings);
		List<BookingSupplier> bookingSuppliers = new ArrayList<BookingSupplier>();
		
		for (Map<String, Object> map : groupHotelBookings) {
			BookingSupplier bSupplier = new BookingSupplier();
			bSupplier.setSupplierName(map.get("supplierName") == null ? "" : map.get("supplierName").toString());
			bSupplier.setRemark(map.get("remark") == null ? "" : map.get("remark").toString());
			List<BookingSupplierDetail> bookingDetails = bookingSupplierDetailBiz.selectByPrimaryBookId((Integer) map.get("bookingId"));
			bSupplier.setDetailList(bookingDetails);
			bookingSuppliers.add(bSupplier);
		}
		result.setBookingSuppliers(bookingSuppliers);
		return result;
	}
	@Override
	public BookingSupplierResult getBookingInfo(Integer groupId,
			Integer supplierType) {
		BookingSupplierResult result = new BookingSupplierResult();
		Map<String, Object> datas = bookingSupplierBiz.selectBookingInfo(groupId, supplierType);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		mapList.add(datas);
		result.setMapList(mapList);
		Boolean checkGroupCanEdit = tourGroupBiz.checkGroupCanEdit(groupId);
		result.setGroupAbleEdit(checkGroupCanEdit);
		return result;
	}
	@Override
	public BookingSupplierResult getMoreBookingInfo(Integer groupId,
			Integer supplierType) {
		BookingSupplierResult result = new BookingSupplierResult();
		Map<String, Object> datas = moreBookingInfoDetail(groupId, supplierType);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		mapList.add(datas);
		result.setMapList(mapList);
		Boolean checkGroupCanEdit = tourGroupBiz.checkGroupCanEdit(groupId);
		result.setGroupAbleEdit(checkGroupCanEdit);
		return result;
	}
	@Override
	public BookingSupplierResult toCarPreview(Integer groupId,
			Integer supplierType) {
		BookingSupplierResult result = new BookingSupplierResult();
		List<GroupRequirement> carList = groupRequirementBiz.selectByGroupIdAndType(groupId, 4);
		result.setGroupRequirements(carList);
		//该团行程信息
		GroupRouteVO vo = groupRouteBiz.findGroupRouteByGroupId(groupId);
		result.setRouteVO(vo);
		TourGroup tg = tourGroupBiz.selectByPrimaryKey(groupId);
		result.setTourGroup(tg);
		//团操作计调
		List<BookingGuide> guides = bookingGuideBiz
				.selectGuidesByGroupId(groupId);
		result.setbGuides(guides);
		List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectBookingSupplierDetailByGroupId(groupId);
		result.setDetailList(details);
		return result;
	}
	@Override
	public Map<String, Object> bookingInfoDetail(Integer groupId,
			Integer supplierType) {
		Map<String, Object> datas = bookingSupplierBiz.selectBookingInfo(groupId, supplierType);
		List<GroupRequirement> groupRequirements = (List<GroupRequirement>) datas.get("requirementInfos");
		if (!CollectionUtils.isEmpty(groupRequirements)) {
			
			for (GroupRequirement req : groupRequirements) {
				GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(req.getOrderId());
				if (groupOrder != null) {
					req.setNameFull(groupOrder.getOrderType() == 1 ? groupOrder.getSupplierName() : "散客团");
					req.setReceiveMode(groupOrder.getReceiveMode() != null ? groupOrder.getReceiveMode() : "");
				} else {
					req.setNameFull("");
					req.setReceiveMode("");
					
				}
				
			}
			
		}
		return datas;
	}
	@Override
	public Map<String, Object> moreBookingInfoDetail(Integer groupId,
			Integer supplierType) {
		Map<String, Object> datas = bookingSupplierBiz.selectBookingInfoPO(groupId, supplierType);
		return datas;
	}
	@Override
	public BookingSupplierResult toEditSupplier(Integer groupId,Integer bookingId) {
		BookingSupplierResult result = new BookingSupplierResult();
		if (bookingId != null) {
			BookingSupplier supplier = bookingSupplierBiz.selectByPrimaryKey(bookingId);
			result.setBookingSupplier(supplier);
			List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
			result.setDetailList(detailList);
			
			if (supplier.getSupplierType().equals(Constants.SCENICSPOT)) {
				List<SupplierItem> supplierItems = supplierItemBiz.findSupplierItemBySupplierId(supplier.getSupplierId());
				result.setSupplierItems(supplierItems);
			}
		}
		
		if (groupId != null) {
			List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
			result.setbGuides(bookingGuides);
		}
		
		return result;
	}
	@Override
	public BookingSupplierResult viewSupplier(Integer groupId,Integer bookingId) {
		BookingSupplierResult result = new BookingSupplierResult();
		BookingSupplier supplier = null;
		if (bookingId != null) {
			supplier  = bookingSupplierBiz.selectByPrimaryKey(bookingId);
			result.setBookingSupplier(supplier);
			List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
			result.setDetailList(detailList);
		}
		List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setbGuides(bookingGuides);
		if (supplier.getSupplierType() == 5) {
			
			List<SupplierItem> sightTypes = supplierItemBiz.findSupplierItemBySupplierId(supplier.getSupplierId());
			result.setSupplierItems(sightTypes);
		}
		
		return result;
	}
	@Override
	public PageBean getFleetGroupList(PageBean page, TourGroupVO tourGroup,
			Set<Integer> set) {
		PageBean pageBean = tourGroupBiz.getFleetGroupList(page, tourGroup, set);
		return pageBean;
	}
	@Override
	public PageBean querySupplierList(PageBean page, TourGroupVO vo,
			Set<Integer> set,Integer supplierType) {
		PageBean pageBean = null;
		if (supplierType.equals(Constants.FLEET)) {
			pageBean = getFleetGroupList(page, vo, set);
		} else {
			pageBean = tourGroupBiz.getGroupInfoList(page, vo, set);
		}
		List<BookingGroup> bookingGroupList = pageBean.getResult();
			if (bookingGroupList != null && bookingGroupList.size() > 0) {
				for (BookingGroup group : bookingGroupList) {
					if (group.getProductBrandName() != null) {
						group.setProductName("【" + group.getProductBrandName() + "】" + group.getProductName());
					}
					//填充定制团的组团社名称
					if (group.getSupplierId() != null) {
						SupplierInfo supplierInfo = supplierBiz.selectBySupplierId(group.getSupplierId());
						if (supplierInfo != null) {
							group.setSupplierName(supplierInfo.getNameFull());
						}
					} 
				}
			}
			
			if (!CollectionUtils.isEmpty(bookingGroupList)) {
				for (BookingGroup bGroup : bookingGroupList) {
					
					List<GroupOrder> gOrders = groupOrderBiz.selectOrderByGroupId(bGroup.getGroupId());
					bGroup.setGroupOrderList(gOrders);
					List<BookingSupplierPO> supplierPOs = bookingSupplierBiz.getBookingSupplierByGroupIdAndSupplierType(bGroup.getGroupId(), supplierType, null, null, null);
					bGroup.setBookingSuppliers(supplierPOs);
					
				}
			}
		return pageBean;
	}
	@Override
	public WebResult<Map<String, Object>> saveBooking(BookingSupplier bookingSupplier, FinanceGuide financeGuide,String bizCode,PlatformEmployeePo curUser) {
		 WebResult<Map<String, Object>> result = new WebResult<Map<String,Object>>();
		//如果未并团，则不对团进行校验
			if (bookingSupplier.getGroupId() != null && bookingSupplier.getGroupId() != 0) {
				if (!tourGroupBiz.checkGroupCanEdit(bookingSupplier.getGroupId())) {
//					return errorJson("该团已审核或封存，不允许修改该信息");
					result.setErrorCode(SaleErrorCode.UNABLE_EDIT_ERROR);
					return result;
				}
			}
			
			
			
			String bookingNo = null;
			if (bookingSupplier.getId() == null) {
				int count = bookingSupplierBiz.getBookingCountByTypeAndTime(bookingSupplier.getSupplierType());
				bookingNo = bizCode + Constants.SUPPLIERSHORTCODEMAP.get(bookingSupplier.getSupplierType()) + new SimpleDateFormat("yyMMdd").format(new Date()) + (count + 100);
				bookingSupplier.setCreateTime((new Date()).getTime());
			}
			
			//产生日志 明细表
			//TODO 待完善
//			List<LogOperator> logList = new ArrayList<LogOperator>();
//			if (bookingSupplier.getId() != null) {
//				for (BookingSupplierDetail detail : bookingSupplier.getDetailList()) {
//					detail.setBookingId(bookingSupplier.getId());
//				}
//				List<BookingSupplierDetail> orginDb = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingSupplier.getId());
//				List<LogOperator> tmpList = LogFieldUtil.getLog_InstantList(curUser.getBizId(), curUser.getName(), "booking_supplier_detail", bookingSupplier.getId(), bookingSupplier.getDetailList(), orginDb);
//				logList.addAll(tmpList);
//			}
//			logService.insert(logList);
			
			int id = bookingSupplierBiz.save(bookingSupplier, bookingNo);
			BookingSupplier supplier = bookingSupplierBiz.selectByPrimaryKey(id);
			
			if (financeGuide != null) {
				//导游报账
				financeGuide.setBookingIdLink(supplier.getId());
				financeGuide.setSupplierType(supplier.getSupplierType());
				financeGuide.setGroupId(supplier.getGroupId());
				financeGuideBiz.financeSave(financeGuide);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bookingId", id);
			map.put("groupId", bookingSupplier.getGroupId());
			map.put("stateBooking", supplier.getStateBooking());
			result.setValue(map);
		return result;
	}
	@Override
	public ResultSupport delDetail(Integer bookingSupplierDetailId) {
		ResultSupport resultSupport = new ResultSupport();
		BookingSupplierDetail detail = bookingSupplierDetailBiz.selectByPrimaryKey(bookingSupplierDetailId);
		BookingSupplier bookingSupplier = bookingSupplierBiz.selectByPrimaryKey(detail.getBookingId());
		List<BookingSupplierDetail> SupplierDetails = bookingSupplierDetailBiz.selectByPrimaryBookId(detail.getBookingId());
		if (SupplierDetails != null && SupplierDetails.size() > 0) {
			bookingSupplier.setTotalCash(bookingSupplier.getTotalCash().subtract(new BigDecimal(detail.getItemTotal())));
			bookingSupplierDetailBiz.deleteByPrimaryKey(bookingSupplierDetailId);
			bookingSupplierBiz.updateByPrimaryKeySelective(bookingSupplier);
		} else {
			bookingSupplier.setTotal(bookingSupplier.getTotal().subtract(new BigDecimal(1)));
			bookingSupplier.setTotalCash(bookingSupplier.getTotalCash().subtract(new BigDecimal(detail.getItemTotal())));
			bookingSupplierDetailBiz.deleteByPrimaryKey(bookingSupplierDetailId);
			bookingSupplierBiz.updateByPrimaryKeySelective(bookingSupplier);

		}
		return resultSupport;
	}
	@Override
	public ResultSupport delBookingSupplier(Integer bookingId, boolean flag) {
		ResultSupport resultSupport = new ResultSupport();
		bookingSupplierBiz.deleteSupplierWithFinanceByPrimaryKey(bookingId, true);
		return resultSupport;
	}
	@Override
	public BookingSupplierResult loadInAndOutcomeData(Integer bookingId,
			Integer groupId) {
		BookingSupplierResult result = new BookingSupplierResult();
		BookingSupplier supplier = bookingSupplierBiz.selectByPrimaryKey(bookingId);
		result.setBookingSupplier(supplier);
		List<BookingSupplierDetail> details = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
		result.setDetailList(details);
		List<BookingGuide> bookingGuides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setbGuides(bookingGuides);
		return result;
	}
	@Override
	public ResultSupport stateConfirm(Integer bookingId) {
		ResultSupport resultSupport = new ResultSupport();
		bookingSupplierBiz.stateConfirm(bookingId);
		return resultSupport;
	}
	@Override
	public List<SupplierContractPrice> findRoomTypeBySupplierId(Integer bizId,
			Integer supplierId) {
		SupplierContract supplierContract = contractBiz.getSupplierContractByBizIdAndSupplierId(bizId, supplierId);

		
		// 根据供应商ID 获得协议
		List<SupplierContractPrice> supplierContractPriceList = contractBiz.getContractPriceListByContractId(supplierContract.getId());
		
		return supplierContractPriceList;
	}
	@Override
	public BookingSupplierResult exportBookingHotel(Map paramMap) {
		BookingSupplierResult result = new BookingSupplierResult();
		List<Map<String, Object>> groupHotelBookings = tourGroupBiz.getGroupHotelBooking(paramMap);
		result.setMapList(groupHotelBookings);
		List<BookingSupplier> bookingSuppliers = new ArrayList<BookingSupplier>();

		for (Map<String, Object> map : groupHotelBookings) {
			BookingSupplier bSupplier = new BookingSupplier();
			bSupplier.setSupplierName(map.get("supplierName") == null ? "" : map.get("supplierName").toString());
			bSupplier.setRemark(map.get("remark") == null ? "" : map.get("remark").toString());
			List<BookingSupplierDetail> bookingDetails = bookingSupplierDetailBiz.selectByPrimaryBookId((Integer) map.get("bookingId"));
			bSupplier.setDetailList(bookingDetails);
			bookingSuppliers.add(bSupplier);
		}
		result.setBookingSuppliers(bookingSuppliers);
		return result;
	}
	@Override
	public List<SupplierContract> selectCashType(Integer bizId,
			Integer supplierId, Integer groupId) {
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		List<SupplierContract> contracts = contractBiz.getContractsBySupplierIdAndDate(bizId, supplierId, tourGroup.getDateStart());
		
		return contracts;
	}
	@Override
	public List<SupplierItem> selectItems(Integer supplierId) {
		List<SupplierItem> supplierItems = supplierItemBiz.findSupplierItemBySupplierId(supplierId);
		return supplierItems;
	}
	@Override
	public ResultSupport deleteBill(Integer bizId, Integer guideId,
			Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		financeBillBiz.deleteBillOrder(bizId, groupId, guideId);
		return resultSupport;
	}
	@Override
	public ResultSupport batchSaveBillDetail(Integer bizId, String userName,
			String jsonStr) {
		ResultSupport resultSupport = new ResultSupport();
		financeBillBiz.batchInsertBillDetail(bizId, userName, jsonStr);
		return resultSupport;
	}
	@Override
	public BookingSupplier savePrice(BookingSupplier bookingSupplier) {
		bookingSupplierBiz.updateByPrimaryKeySelective(bookingSupplier);
		BookingSupplier bs = bookingSupplierBiz.selectByPrimaryKey(bookingSupplier.getId());
		return bs;
	}
	@Override
	public BookingSupplierResult bookingSupplierExport(Integer bookingId,Integer bizId) {
		BookingSupplierResult result = new BookingSupplierResult();
		BookingSupplier supplier = bookingSupplierBiz.selectByPrimaryKey(bookingId);
		result.setBookingSupplier(supplier);
		//团信息
		TourGroup groupInfo = tourGroupBiz.selectByPrimaryKey(supplier.getGroupId());
		result.setTourGroup(groupInfo);
		//导游
		BookingGuide guide = bookingGuideBiz.selectByGroupId(supplier.getGroupId());
		List<BookingGuide> bookingGuides = new ArrayList<BookingGuide>();
		bookingGuides.add(guide);
		result.setbGuides(bookingGuides);
		//订单详情
		List<BookingSupplierDetail> detailList = bookingSupplierDetailBiz.selectByPrimaryBookId(bookingId);
		result.setDetailList(detailList);
		
		
		//备注
		//客人名单
		//酒店
		if (supplier.getSupplierType().equals(Constants.HOTEL)) {
			//团队不输出，散客输出
			if (groupInfo.getGroupMode() == 0) {
				List<String> hotelList = tourGroupBiz.selectGroupRequirementByGroupId(supplier.getGroupId(), Constants.HOTEL);
				result.setStrList(hotelList);
			} 
		}
		//其他供应商
		else {
			Map parameters = new HashMap();
			parameters.put("groupId", supplier.getGroupId());
			parameters.put("supplierId", supplier.getSupplierId());
			parameters.put("bizId" ,bizId);
			List<GroupOrderGuest> guests = groupOrderGuestBiz.getGroupOrderGuests(parameters);
			StringBuilder sb = new StringBuilder();
			String gender = "男";
			String certificateName = "身份证";
			for (GroupOrderGuest guest : guests) {
				if (guest.getGender() != 1) {
					gender = "女";
				}
				List<DicInfo> certificateTypeList = dicBiz.getListByTypeCode(BasicConstants.GYXX_ZJLX);
				for (DicInfo dicInfo : certificateTypeList) {
					if (dicInfo.getId() == guest.getCertificateTypeId()) {
						certificateName = dicInfo.getValue();
					}
				}
				sb.append(guest.getName() + " " + gender + "  " + certificateName
						+ "  " + guest.getCertificateNum() + ";");
			}
//			remarkMap.put("customers", toGetGuestString(guests));
			result.setCustomers(sb.toString());
		}
		if (supplier.getSupplierType() == 4) {
			//行程
			List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(groupInfo.getId());
			result.setGroupRoutes(routeList);
		}
		return result;
	}
	@Override
	public PageBean getHotelGroupList(PageBean pageBean, TourGroupVO tourGroup,
			Set<Integer> set) {
		 PageBean page = tourGroupBiz.getHotelGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getOutcomeGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getOutcomeGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getRestaurantGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getRestaurantGroupList(pageBean, tourGroup, set);
		return page;
		
	}
	@Override
	public PageBean getEntertaimentGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getEntertaimentGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getGolfGroupList(PageBean pageBean, TourGroupVO tourGroup,
			Set<Integer> set) {
		PageBean page = tourGroupBiz.getGolfGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getSightGroupList(PageBean pageBean, TourGroupVO tourGroup,
			Set<Integer> set) {
		PageBean page = tourGroupBiz.getSightGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getAirTicketGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getAirTicketGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getTrainTicketGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getTrainTicketGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getInsuranceGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getInsuranceGroupList(pageBean, tourGroup, set);
		return page;
	}
	@Override
	public PageBean getIncomeGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean page = tourGroupBiz.getIncomeGroupList(pageBean, tourGroup, set);
		return page;
	}

}

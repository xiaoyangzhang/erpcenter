
package com.yimayhd.erpcenter.facade.sales.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.errorcode.SaleErrorCode;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpcenter.facade.sales.result.operation.BookingDeliveryResult;
import com.yimayhd.erpcenter.facade.sales.service.BookingDeliveryFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

/**
 * @ClassName: BookingDeliveryFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class BookingDeliveryFacadeImpl implements BookingDeliveryFacade {

	@Autowired
	private BookingDeliveryBiz bookingDeliveryBiz;
	@Autowired
	private BookingDeliveryPriceBiz bookingDeliveryPriceBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private SupplierBiz supplierBiz;
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private DicBiz dicBiz;
	@Override
	public BookingDeliveryResult getDeliveryBookingDetailList(Integer groupId) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		List<BookingDelivery> list = bookingDeliveryBiz.getDeliveryListByGroupId(groupId);
        if (list != null && list.size() > 0) {
            for (BookingDelivery delivery : list) {
                delivery.setPriceList(bookingDeliveryPriceBiz.getPriceListByBookingId(delivery.getId()));
            }
        }
        result.setBookingDeliveries(list);
		return result;
	}
	
	@Override
	public BookingDeliveryResult getDeliveryBookingArrangeList(Integer groupId) {
		BookingDeliveryResult bookingDeliveryResult = getDeliveryBookingDetailList(groupId);
		bookingDeliveryResult.setGroupCanEdit(tourGroupBiz.checkGroupCanEdit(groupId));
		return bookingDeliveryResult;
	}

	
	@Override
	public BookingDeliveryResult getDeliveryEditInfo(Integer gid,Integer bid) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		TourGroup groupInfo = tourGroupBiz.selectByPrimaryKey(gid);
		result.setTourGroup(groupInfo);
        List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(gid);
        result.setGroupRoutes(routeList);
        if (groupInfo.getGroupMode() < 1) {
            List<GroupOrder> orderList = groupOrderBiz.selectOrderByGroupId(gid);
            if (orderList != null && orderList.size() > 0) {
                for (GroupOrder order : orderList) {
                    SupplierInfo supplierInfo = supplierBiz.selectBySupplierId(order.getSupplierId());
                    if (supplierInfo != null) {
                        order.setSupplierName(supplierInfo.getNameFull());
                    }
                }
            }
            result.setGroupOrders(orderList);
        }
        
        if (bid != null) {
            BookingDelivery delivery = bookingDeliveryBiz.getBookingInfoById(bid);
            result.setBookingDelivery(delivery);
        }
		return result;
	}

	
	@Override
	public WebResult<Map<String, Object>> saveDeliveryBooking(String bookingStr,PlatformEmployeePo userInfo) {
		WebResult<Map<String, Object>> result = new WebResult<Map<String, Object>>(); 
		BookingDelivery bookingDelivery = JSON.parseObject(bookingStr, BookingDelivery.class);
        if (!tourGroupBiz.checkGroupCanEdit(bookingDelivery.getGroupId())) {
           // return errorJson("该团已审核或封存，不允许修改该信息");
        	result.setErrorCode(SaleErrorCode.UNABLE_EDIT_ERROR);
        	return result;
        }
        if (bookingDelivery.getId() == null) {
            bookingDelivery.setUserId(userInfo.getEmployeeId());
            bookingDelivery.setUserName(userInfo.getName());
            bookingDelivery.setStateBooking(0);
            bookingDelivery.setStateFinance(0);
            bookingDelivery.setBookingDate(new Date());
            bookingDelivery.setCreateTime(System.currentTimeMillis());
        }
        int id = bookingDeliveryBiz.saveBooking(bookingDelivery);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupId", bookingDelivery.getGroupId());
        map.put("bookingId", id);
        result.setValue(map);
		return result;
	}

	
	@Override
	public ResultSupport angencyConfirm(Integer id) {
		ResultSupport result = new ResultSupport();
		bookingDeliveryBiz.angencyConfirm(id);
		return result;
	}

	
	@Override
	public ResultSupport angencyDelete(Integer id) {
		ResultSupport result = new ResultSupport();
		bookingDeliveryBiz.angencyDelete(id);
		return result;
	}

	
	@Override
	public BookingDeliveryResult getBookingDeliveryInfo(Integer bookingId,Integer bizId) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		BookingDelivery bookingDelivery = bookingDeliveryBiz.getBookingInfoById(bookingId);
		result.setBookingDelivery(bookingDelivery);
		
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(bookingDelivery.getGroupId());
		result.setTourGroup(tourGroup);
		
		List<BookingSupplierDetail> driversByGroupIdAndType = bookingSupplierDetailBiz.getDriversByGroupIdAndType(tourGroup.getId(), Constants.FLEET);
		result.setSupplierDetails(driversByGroupIdAndType);
		
		List<GroupRoute> groupRoutes = groupRouteBiz.selectByGroupIdAndBookingId(tourGroup.getId(),bookingDelivery.getId());
		result.setGroupRoutes(groupRoutes);
		
		List<BookingGuide> bookingGuides = bookingGuideBiz.selectByGroupId2(tourGroup.getId());
		result.setBookingGuides(bookingGuides);
		
		List<GroupOrder> orderList = null;
		if (tourGroup.getGroupMode() > 0) {//团队
            orderList = groupOrderBiz.selectOrderByGroupId(tourGroup.getId());
        } else {//散客
            List<BookingDeliveryOrder> deliveryOrderList = bookingDelivery.getOrderList();
            orderList = new ArrayList<GroupOrder>();
            if (deliveryOrderList != null && deliveryOrderList.size() > 0) {
                for (BookingDeliveryOrder deliveryOrder : deliveryOrderList) {
                    orderList.add(groupOrderBiz.selectByPrimaryKey(deliveryOrder.getOrderId()));
                }
            }
        }
		result.setGroupOrders(orderList);
		
		if (!CollectionUtils.isEmpty(orderList)) {
			
			List<GroupOrderGuest> groupOrderGuests = groupOrderGuestBiz.selectByOrderId(orderList.get(0).getId());
			result.setNotGuests(groupOrderGuests);
		}
		List<GroupOrderPrintPo> gopps = new ArrayList<GroupOrderPrintPo>();
	    GroupOrderPrintPo gopp = null;
        // 房量总计
        String total = "";
        if (tourGroup != null && tourGroup.getGroupMode() < 1) {
            for (GroupOrder order : orderList) {
                // 拿到单条订单信息
                gopp = new GroupOrderPrintPo();
                gopp.setPlace((order.getProvinceName() == null ? "" : order.getProvinceName())
                        + (order.getCityName() == null ? "" : order.getCityName()));
                gopp.setRemark(order.getRemarkInternal());
                // 根据散客订单统计客人信息
                List<GroupOrderGuest> guests = groupOrderGuestBiz.selectByOrderId(order.getId());
                //客人-接送方式
                gopp.setGuesStatic(order.getReceiveMode());
                gopp.setGuests(guests);
                
                // 根据散客订单统计人数
                Integer numAdult = groupOrderGuestBiz.selectNumAdultByOrderID(order.getId());
                Integer numChild = groupOrderGuestBiz.selectNumChildByOrderID(order.getId());
                gopp.setPersonNum(numAdult + "+" + numChild);
                // 根据散客订单统计酒店信息
                List<GroupRequirement> grogShopList = groupRequirementBiz.selectByOrderAndType(order.getId(), 3);
                if (grogShopList.size() > 0) {
                    if (grogShopList.get(0).getHotelLevel() != null) {
                        gopp.setHotelLevel(dicBiz.getById(grogShopList.get(0).getHotelLevel()).getValue()
                                + "\n");
                    }
                }
                gopp.setGroupRequirements(grogShopList);
                // 省外交通
                // 根据散客订单统计接机信息
                List<GroupOrderTransport> groupOrderTransports = groupOrderTransportBiz.selectByOrderId(order.getId());
                gopp.setOrderTransports(groupOrderTransports);
                gopps.add(gopp);
            }
        }
        result.setOrderPrints(gopps);
        if (tourGroup != null && tourGroup.getGroupMode() > 0) {
        	 Map parameters = new HashMap();
             parameters.put("groupId", tourGroup.getId());
             parameters.put("supplierId", null);
             parameters.put("bizId", bizId);
        	List<GroupOrderGuest> orderGuests = groupOrderGuestBiz.getGroupOrderGuests(parameters);
        	result.setGuests(orderGuests);
		}
		return result;
	}

	

	@Override
	public BookingDeliveryResult getDeliveryPriceInfo(Integer bizId,
			PageBean pageBean, Set<Integer> set, String carInfo) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		if (StringUtils.isNotBlank(carInfo)) {
			
            List<Integer> groupIds = bookingSupplierBiz.getGroupIdByCarInfo(carInfo);
            if (groupIds != null && groupIds.size() > 0) {
                String groupIdStr = groupIds.toString().substring(1, groupIds.toString().length() - 1);
                BookingDeliveryPrice bookingDeliveryPrice = (BookingDeliveryPrice)pageBean.getParameter();
                bookingDeliveryPrice.setGroupIds(groupIdStr);
            }
        }
        Map<String, Object> sum = bookingDeliveryPriceBiz.getSupplierPriceTotal(pageBean, bizId, set);
        result.setMap(sum);
        PageBean page = bookingDeliveryPriceBiz.getSupplierPriceListPage(pageBean, bizId, set);
        result.setPageBean(page);
		return result;
	}

	@Override
	public PageBean getLocalTravelAngencyGroupList(PageBean pageBean,
			TourGroupVO tourGroup, Set<Integer> set) {
		PageBean bean = tourGroupBiz.getLocalTravelAngencyGroupList(pageBean, tourGroup, set);
		return bean;
	}

}

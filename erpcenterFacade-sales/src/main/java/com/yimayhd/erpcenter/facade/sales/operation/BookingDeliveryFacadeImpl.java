
package com.yimayhd.erpcenter.facade.sales.operation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
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
            //PlatformEmployeePo userInfo = WebUtils.getCurUser(request);
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
	public BookingDeliveryResult getBookingDeliveryInfo(Integer bookingId) {
		BookingDeliveryResult result = new BookingDeliveryResult();
		BookingDelivery bookingDelivery = bookingDeliveryBiz.getBookingInfoById(bookingId);
		result.setBookingDelivery(bookingDelivery);
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(bookingDelivery.getGroupId());
		result.setTourGroup(tourGroup);
		return result;
	}

}

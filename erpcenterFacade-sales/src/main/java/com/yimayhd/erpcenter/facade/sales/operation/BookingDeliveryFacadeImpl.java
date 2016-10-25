/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.operation;

import java.util.List;

import org.erpcenterFacade.sales.client.operation.BookingDeliveryFacade;
import org.erpcenterFacade.sales.client.result.BookingDeliveryResult;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

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
		TourGroup groupInfo = tourGroupBiz.selectByPrimaryKey(gid);
        List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(gid);
        if (groupInfo.getGroupMode() < 1) {
            List<GroupOrder> orderList = groupOrderBiz.selectOrderByGroupId(gid);
            if (orderList != null && orderList.size() > 0) {
                for (GroupOrder order : orderList) {
//                    SupplierInfo supplierInfo = supplierSerivce.selectBySupplierId(order.getSupplierId());
//                    if (supplierInfo != null) {
//                        order.setSupplierName(supplierInfo.getNameFull());
//                    }
                }
            }
        }
        
        if (bid != null) {
            BookingDelivery delivery = bookingDeliveryBiz.getBookingInfoById(bid);
        }
		return null;
	}

}

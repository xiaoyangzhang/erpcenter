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
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;

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
	public BookingDeliveryResult DeliveryBookingArrangeList(Integer groupId) {
		BookingDeliveryResult bookingDeliveryResult = getDeliveryBookingDetailList(groupId);
		bookingDeliveryResult.setGroupCanEdit(tourGroupBiz.checkGroupCanEdit(groupId));
		return bookingDeliveryResult;
	}

}

/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.facade.sales.query.BookingDeliveryQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.operation.BookingSupplierResult;
import com.yimayhd.erpcenter.facade.sales.service.BookingSupplierFacade;

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
	@Override
	public BookingSupplierResult getDeliveryExportInfo(BookingDeliveryQueryDTO dto) {
//		BookingDelivery bookingDelivery = bookingDeliveryBiz.getBookingInfoById(dto.getBookingId());
//		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(bookingDelivery.getGroupId());
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
//		List<GroupOrder> groupOrders = groupOrderBiz.selectOrderByGroupId(dto.getGroupId());
//		result.setOrderList(groupOrders);
		if (!CollectionUtils.isEmpty(orderList)) {
			
			List<GroupOrderGuest> groupOrderGuests = groupOrderGuestBiz.selectByOrderId(orderList.get(0).getId());
			result.setGuestList(groupOrderGuests);
		}
		return result;
	}

}

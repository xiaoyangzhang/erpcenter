package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.util.List;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingDeliveryBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingDeliveryDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingDeliveryStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BookingDeliveryBizImpl implements BookingDeliveryBiz {
    Logger logger = LoggerFactory.getLogger(BookingDeliveryBizImpl.class);
    @Autowired
    private BookingDeliveryDal bookingDeliveryDal;


    @Override
    public BookingDeliveryStatics getStaticsByGroupId(Integer groupId) {
        return bookingDeliveryDal.getStaticsByGroupId(groupId);
    }

    @Override
    public List<BookingDelivery> getDeliveryListByGroupId(Integer groupId) {
        List<BookingDelivery> bookingDeliveries = bookingDeliveryDal.getDeliveryListByGroupId(groupId);
//		  for (BookingDelivery bd : bookingDeliveries) {
//			
//			  List<BookingDeliveryOrder> orderList = deliveryOrderDao.getOrderListByBookingId(bd.getId());
//			  bd.setOrderList(orderList);
//		}
        return bookingDeliveries;
    }

    /**
     * 添加默认级别事务，保存失败后回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int saveBooking(BookingDelivery bookingDelivery) {
        return bookingDeliveryDal.saveBooking(bookingDelivery);
    }

    @Override
    public BookingDelivery getBookingInfoById(Integer bookingId) {
        return bookingDeliveryDal.getBookingInfoById(bookingId);
    }

    @Override
    public void angencyConfirm(Integer id) {
        bookingDeliveryDal.angencyConfirm(id);
    }

    public void agencyStateChange(Integer id) {
        bookingDeliveryDal.angencyConfirm(id);
    }

    @Override
    public void financeAudit(Integer id) {
        ;
        bookingDeliveryDal.financeAudit(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void angencyDelete(Integer id) throws ClientException {
        bookingDeliveryDal.angencyDelete(id);
    }

    /**
     * 修改散客团人数时更新地接社人数
     *
     * @param groupId
     * @return
     */
    @Override
    public int updateDeliveryGuestCountOnModifySKGuestCount(Integer groupId) {
        return bookingDeliveryDal.updateDeliveryGuestCountOnModifySKGuestCount(groupId);
    }

    /**
     * 修改团队人数时更新地接社人数
     *
     * @param groupId
     * @param adultCnt
     * @param childCnt
     * @param guideCnt
     * @return
     */
    @Override
    public int updateDeliveryGuestCountOnModifyTeamGuestCount(
            Integer groupId, Integer adultCnt, Integer childCnt,
            Integer guideCnt) {
        return bookingDeliveryDal.updateDeliveryGuestCountOnModifyTeamGuestCount(
                groupId, adultCnt, childCnt,
                guideCnt);
    }

    @Override
    public List<BookingDelivery> selectInitDeliveryList(Integer groupId) {
        return bookingDeliveryDal.selectInitDeliveryList(groupId);
    }

	/*@Override
    public BookingDelivery getBookingDeliveryByOrderId(Integer orderId) {
		return deliveryDao.getBookingDeliveryByOrderId(orderId);
	}
	*/

}

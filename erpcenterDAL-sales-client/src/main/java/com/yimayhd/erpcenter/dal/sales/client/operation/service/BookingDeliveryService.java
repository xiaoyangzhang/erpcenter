package com.yimayhd.erpcenter.dal.sales.client.operation.service;

import java.util.List;

import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingDeliveryStatics;
import org.apache.ibatis.annotations.Param;


public interface BookingDeliveryService {
	BookingDeliveryStatics getStaticsByGroupId(Integer groupId);
	List<BookingDelivery> getDeliveryListByGroupId(Integer groupId);
	int saveBooking(BookingDelivery bookingDelivery);	
	BookingDelivery getBookingInfoById(Integer bookingId);
	/**
	 * 地接社确认
	 * @param id
	 * @throws ClientException
	 */
	void angencyConfirm(Integer id);
	/**
	 * 财务审核
	 * @param id
	 */
	void financeAudit(Integer id);
	/**
	 * 删除下接单
	 * @param id
	 */
	void angencyDelete(Integer id) throws ClientException;
	//BookingDelivery getBookingDeliveryByOrderId(Integer orderId);
	
	/**
	 * 修改散客团人数时更新地接社人数
	 * @param groupId
	 * @return
	 */
	int updateDeliveryGuestCountOnModifySKGuestCount(Integer groupId);
	/**
	 * 修改团队人数时更新地接社人数
	 * @param groupId
	 * @param adultCnt
	 * @param childCnt
	 * @param guideCnt
	 * @return
	 */
	int updateDeliveryGuestCountOnModifyTeamGuestCount(Integer groupId,Integer adultCnt,Integer childCnt,Integer guideCnt);
	/**
	 * 期末数据-地接社
	 * @param id
	 * @return
	 */
	List<BookingDelivery> selectInitDeliveryList(Integer groupId);
}

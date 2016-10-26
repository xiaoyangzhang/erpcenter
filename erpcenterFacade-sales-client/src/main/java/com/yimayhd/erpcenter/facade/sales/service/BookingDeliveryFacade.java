/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service;

import java.util.Map;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.result.BookingDeliveryResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;

/**
 * @ClassName: BookingDeliveryFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public interface BookingDeliveryFacade {
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:地接社订单明细列表
	* @param 
	* @return List<BookingDelivery>
	* @throws
	 */
	BookingDeliveryResult getDeliveryBookingDetailList(Integer groupId);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:预定安排地接社
	* @param 
	* @return List<BookingDelivery>
	* @throws
	 */
	BookingDeliveryResult getDeliveryBookingArrangeList(Integer groupId);
	/**
	 * 新增/编辑地接订单
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:
	* @param 
	* @return BookingDeliveryResult
	* @throws
	 */
	BookingDeliveryResult getDeliveryEditInfo(Integer groupId,Integer bid);
	/**
	 * 保存地接计调订单
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:
	* @param 
	* @return ResultSupport
	* @throws
	 */
	WebResult<Map<String, Object>> saveDeliveryBooking(String bookingStr,PlatformEmployeePo userInfo);
	
	ResultSupport angencyConfirm(Integer id);
	
	ResultSupport angencyDelete(Integer id);
}

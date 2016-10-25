/**
 * 
 */
package org.erpcenterFacade.sales.client.operation;

import java.util.List;

import org.erpcenterFacade.sales.client.result.BookingDeliveryResult;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;

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
	
}

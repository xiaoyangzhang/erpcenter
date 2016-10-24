package com.yimayhd.erpcenter.biz.sales.client.service.airticket;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketOrder;

public interface AirTicketOrderDal {
	
	
	/**
	 * 增加订单
	 * @param airTicketOrder
	 * @return
	 */
	Integer saveOrder(AirTicketOrder airTicketOrder);
	
	
	/**
	 * 保存一个申请下多个游客的订单。
	 * @param orderList
	 * @return
	 */
	Integer saveOrderList(List<AirTicketOrder> orderList);
	
	/**
	 * 删除订单，要求条件：
	 * 	1、业务主键(orderId)不能为空
	 *  2、商家ID(orderId)不能为空
	 * @param primaryKey
	 * @param bizId
	 * @return
	 * @throws Exception 
	 */
	void deleteOrder(Integer orderId,Integer bizId);
	
	/**
	 * 更新Order，用于修改价格和备注
	 */
	void updateOrder(AirTicketOrder airTicketOrder);
	
	/**
	 * 删除request下所有order，更新request时先删后加
	 * @param requestId
	 * @param bizId
	 */
	void deleteRequestOrder(Integer requestId,Integer bizId);
	
	/**
	 * 查询当前请求(reqquest)下所有的机票订单，要求条件：
	 * 	1、请求业务主键（requestId）不能为空
	 * 　 2、商家ID（bizId）不能为空
	 * @param reequestId
	 * @param bizId
	 * @return
	 * @throws Exception 
	 */
	List<AirTicketOrder> findOrderList(Integer requestId,Integer bizId);
}

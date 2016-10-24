package com.yimayhd.erpcenter.biz.sales.client.service.b2b;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;

/**
 * Created by zm on 2016/6/17.
 */
public interface B2BGroupOrderDal {

	/**
	 * B2B 查询销售订单信息
	 *
	 * @param dateStart  出团日期
	 * @param dateEnd    出团结束日期
	 * @param clientName 组团社名称
	 * @return 销售订单情况
	 */
	List<GroupOrder> findGroupOrder(Integer bizId, String dateStart, String dateEnd, String clientName,Integer exports);

	/**
	 * B2B 更新导出订单状态
	 *
	 * @param ids 订单IDs
	 * @return 是否成功
	 */
	boolean updateB2bExportState(String ids);

	/**
	 * B2B 查询某些订单的详细信息
	 *
	 * @param ids 订单IDs
	 * @return 某些订单的详细信息
	 */
	List<GroupOrder> findGroupOrderDetailByIds(String ids);

	/**
	 * B2B 查询某些订单的游客信息
	 *
	 * @param orderId 订单orderId
	 * @return 某些订单的游客信息
	 */
	List<GroupOrderGuest> findGroupOrderGuestByOrderId(Integer orderId);

	/**
	 * B2B 查询某些订单的价格信息
	 *
	 * @param orderId 订单orderId
	 * @return 某些订单的价格信息
	 */
	List<GroupOrderPrice> findGroupOrderPriceByOrderId(Integer orderId);

	/**
	 * B2B 查询某些订单的行程信息
	 *
	 * @param orderId 订单orderId
	 * @return 某些订单的行程信息
	 */
	List<GroupRoute> findGroupRouteByOrderId(Integer orderId);
}

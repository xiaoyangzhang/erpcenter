package com.yimayhd.erpcenter.biz.sales.client.service.car;

import java.util.List;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.query.SynHotelQuery;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchDeliveryPriceResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchOrderGuestResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchTransportsResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SynHotelResult;

public interface DoubleCarBiz {
	/**
	 * 根据团id查询航班信息
	 * @param orderId
	 * @return
	 * @author wangjun
	 */
	SearchTransportsResult selectTransportByOrderId(int orderId);
	/**
	 * 根据多个团id查询航班信息
	 * @param orderIds  1,2,3
	 * @return
	 * @author wangjun
	 */
	SearchTransportsResult selectTransportByOrderIds(String orderIds);
	
	SearchDeliveryPriceResult selectDeliveryPrice(int orderId, int page, int pageSize);
	
	/**
	 * 根据多个orderId查询记录
	 * @param orderIds   	例如：9001,9005,1002
	 * @param page
	 * @param pageSize
	 * @return
	 */
	SearchDeliveryPriceResult selectDeliveryPrice(List<Integer> orderIds, int page, int pageSize);
	
	SearchOrderGuestResult selectOrderGuest(int orderId, int page, int pageSize);
	
	/**
	 * 根据多个orderId查询记录
	 * @param orderIds	例如：2,3,4
	 * @param page
	 * @param pageSize
	 * @return
	 */
	SearchOrderGuestResult selectOrderGuest(List<Integer> orderIds, int page, int pageSize);
	
	/**
	 * 同步酒店
	 * @param query
	 * @return
	 * @author wangjun
	 */
	SynHotelResult synHotelMsg(SynHotelQuery query);

}

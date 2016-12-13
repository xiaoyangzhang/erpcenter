package com.yimayhd.erpcenter.biz.sales.client.service.car;

import java.util.List;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchDeliveryPriceResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchTransportsResult;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;

public interface DoubleCarBiz {
	List<TransPort> selectTransportByOrderId(int orderId);

	SearchTransportsResult selectTransportByOrderIds(String orderIds);
	
	SearchDeliveryPriceResult selectDeliveryPrice(int orderId, int page, int pageSize);

}

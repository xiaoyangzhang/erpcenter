package com.yimayhd.erpcenter.biz.sales.client.service.car;

import java.util.List;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchTransportsResult;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.sales.query.TransportQuery;

public interface DoubleCarBiz {
	List<TransPort> selectTransportByOrderId(int orderId);

	SearchTransportsResult selectTransportByOrderIds(String orderIds);
}

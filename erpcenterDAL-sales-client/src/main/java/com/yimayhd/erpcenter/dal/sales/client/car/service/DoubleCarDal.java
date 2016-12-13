package com.yimayhd.erpcenter.dal.sales.client.car.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;

public interface DoubleCarDal {
	List<TransPort> selectTransportByOrderId(int orderId);

	List<TransPort> selectTransportByOrderIds(String orderIds);
}

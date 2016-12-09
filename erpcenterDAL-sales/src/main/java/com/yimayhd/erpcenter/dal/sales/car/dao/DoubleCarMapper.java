package com.yimayhd.erpcenter.dal.sales.car.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPortHotel;

public interface DoubleCarMapper {

	List<TransPort> selectTransportByOrderId(int orderId);

	List<TransPortHotel> selectTransPortHotelByGroupIds(String groupIds);

}

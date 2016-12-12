package com.yimayhd.erpcenter.biz.sales.service.impl.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.car.DoubleCarBiz;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.service.DoubleCarDal;

public class DoubleCarBizImpl implements DoubleCarBiz{
	@Autowired
	private DoubleCarDal doubleCarDal;

	@Override
	public List<TransPort> selectTransportByOrderId(int orderId) {
		return doubleCarDal.selectTransportByOrderId(orderId);
	}

}

package com.yimayhd.erpcenter.biz.sales.service.impl.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.car.DoubleCarBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.errorCode.DoubleCarErrorCode;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchDeliveryPriceResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchTransportsResult;
import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.service.DoubleCarDal;

public class DoubleCarBizImpl implements DoubleCarBiz{
	@Autowired
	private DoubleCarDal doubleCarDal;

	@Override
	public List<TransPort> selectTransportByOrderId(int orderId) {
		return doubleCarDal.selectTransportByOrderId(orderId);
	}
	
	@Override
	public SearchTransportsResult selectTransportByOrderIds(String orderIds) {
		SearchTransportsResult result = new SearchTransportsResult();
		if(null == orderIds){
			result.setErrorCode(DoubleCarErrorCode.PARAM_ERROR);
		}
		List<TransPort> lists = doubleCarDal.selectTransportByOrderIds(orderIds);
		result.setTransPorts(lists);
		return result;
	}

	@Override
	public SearchDeliveryPriceResult selectDeliveryPrice(int orderId, int page, int pageSize) {
		
		SearchDeliveryPriceResult result = new SearchDeliveryPriceResult();
		try{
			List<BookingDeliveryPrice> list = doubleCarDal.selectDeliveryPrice(orderId+"", page, pageSize);
			result.setPriceList(list);
		}catch(Exception ex){
			result.setErrorCode(DoubleCarErrorCode.QUERY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

}

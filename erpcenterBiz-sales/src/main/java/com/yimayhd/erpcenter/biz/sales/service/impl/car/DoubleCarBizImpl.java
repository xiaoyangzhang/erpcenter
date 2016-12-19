package com.yimayhd.erpcenter.biz.sales.service.impl.car;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.car.DoubleCarBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.errorCode.DoubleCarErrorCode;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.query.SynHotelQuery;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchDeliveryPriceResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchOrderGuestResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SearchTransportsResult;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.result.SynHotelResult;
import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.service.DoubleCarDal;

public class DoubleCarBizImpl implements DoubleCarBiz{
	@Autowired
	private DoubleCarDal doubleCarDal;

	@Override
	public SearchTransportsResult selectTransportByOrderId(int orderId) {
		SearchTransportsResult result = new SearchTransportsResult();
		result.setTransPorts(doubleCarDal.selectTransportByOrderId(orderId));
		return result;
	}
	
	@Override
	public SearchTransportsResult selectTransportByOrderIds(List<Integer> orderIds) {
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
		List<Integer> orderIdList = new ArrayList<Integer>();
		orderIdList.add(orderId);
		SearchDeliveryPriceResult result = selectDeliveryPrice(orderIdList, page, pageSize);
		return result;
	}

	@Override
	public SearchDeliveryPriceResult selectDeliveryPrice(List<Integer> orderIds, int page, int pageSize) {
		SearchDeliveryPriceResult result = new SearchDeliveryPriceResult();
		try{
			PageBean<BookingDeliveryPrice> pbResult = doubleCarDal.selectDeliveryPrice(orderIds, page, pageSize);
			result.setPriceList(pbResult.getResult());
			result.setTotalCount(pbResult.getTotalCount());
			result.setTotalPage(pbResult.getTotalPage());
		}catch(Exception ex){
			result.setErrorCode(DoubleCarErrorCode.QUERY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public SearchOrderGuestResult selectOrderGuest(int orderId, int page, int pageSize) {
		List<Integer> orderIdList = new ArrayList<Integer>();
		orderIdList.add(orderId);
		SearchOrderGuestResult result = selectOrderGuest(orderIdList, page, pageSize);
		return result;
	}

	@Override
	public SearchOrderGuestResult selectOrderGuest(List<Integer> orderIds, int page, int pageSize) {
		
		SearchOrderGuestResult result = new SearchOrderGuestResult();
		try{
			PageBean<GroupOrderGuest> pbResult = doubleCarDal.selectOrderGuestListPage(orderIds, page, pageSize);
			result.setGuestList(pbResult.getResult());
			result.setTotalCount(pbResult.getTotalCount());
			result.setTotalPage(pbResult.getTotalPage());
		}catch(Exception ex){
			result.setErrorCode(DoubleCarErrorCode.QUERY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public SynHotelResult synHotelMsg(SynHotelQuery query) {
		SynHotelResult result = new SynHotelResult();
		if(null == query){
			result.setErrorCode(DoubleCarErrorCode.QUERY_ERROR);
		}
		result.setHotelMsgs(doubleCarDal.synHotelMsg(query.getGroupId(),query.getType(),query.getDepartureDate(),query.getArrivalDate()));
		return result;
	}

	@Override
	public List<SynHotelResult> batchSynHotelMsg(List<SynHotelQuery> querys) {
		if(null == querys || querys.size() <=0){
			return null;
		}
		List<SynHotelResult> synHotelResults = new ArrayList<SynHotelResult>();
		for (SynHotelQuery query : querys) {
			SynHotelResult result = new SynHotelResult();
			if(null == query){
				result.setErrorCode(DoubleCarErrorCode.QUERY_ERROR);
			}
			result.setGroupId(query.getGroupId());
			result.setType(query.getType());
			result.setDepartureDate(query.getDepartureDate());
			result.setArrivalDate(query.getArrivalDate());
			result.setHotelMsgs(doubleCarDal.synHotelMsg(query.getGroupId(),query.getType(),query.getDepartureDate(),query.getArrivalDate()));
			synHotelResults.add(result);
		}
		return synHotelResults;
	}

}

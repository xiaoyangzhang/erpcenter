package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPortHotel;
import com.yimayhd.erpcenter.dal.sales.client.car.service.DoubleCarDal;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.finance.dao.DoubleCarMapper;

public class DoubleCarDalImpl implements DoubleCarDal{
	
	@Autowired
	private DoubleCarMapper doubleCarMapper;
	
	@Override
	public List<TransPort> selectTransportByOrderId(int orderId) {
		List<TransPort> list = doubleCarMapper.selectTransportByOrderId(orderId);
		String groupIds = list2String(list);
		List<TransPortHotel> hotels = doubleCarMapper.selectTransPortHotelByGroupIds(groupIds);
		for (int i = 0;i <= list.size();i++) {
			TransPort transPort = list.get(i);
			Date date;
			if(transPort.getType() == 0){//接
				date = transPort.getArrivalDate();
			}else{//送
				date = transPort.getDepartureDate();
			}
			StringBuffer nameBuffer = new StringBuffer();
			for (int j = 0; j < hotels.size(); j++) {
				TransPortHotel hotel = hotels.get(j);
				if(null != date && hotel.getGroupId() == transPort.getGroupId() && hotel.getItemDate().compareTo(date) == 0){
					if(transPort.getHotelId() == 0){
						transPort.setHotelId(hotel.getSupplierId());
					}
					nameBuffer.append(hotel.getSupplierName());
					if(j < hotels.size() - 1 ){
						nameBuffer.append(",");
					}
				}
			}
			transPort.setHotelName(nameBuffer.toString());
		}
		return list;
	}
	
	
	private static String list2String(List<TransPort> list){
		if(list.size() <=0){
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		boolean flag = false;
		for (TransPort transPort : list) {
			if(flag){
				buffer.append(",");
			}else{
				flag = true;
			}
			buffer.append(transPort.getGroupId());
		}
		return buffer.toString();
	}

	@Override
	public List<TransPort> selectTransportByOrderIds(String orderIds) {
		List<TransPort> list = doubleCarMapper.selectTransportByOrderIds(orderIds);
		String groupIds = list2String(list);
		List<TransPortHotel> hotels = doubleCarMapper.selectTransPortHotelByGroupIds(groupIds);
		for (int i = 0;i <= list.size();i++) {
			TransPort transPort = list.get(i);
			Date date;
			if(transPort.getType() == 0){//接
				date = transPort.getArrivalDate();
			}else{//送
				date = transPort.getDepartureDate();
			}
			StringBuffer nameBuffer = new StringBuffer();
			for (int j = 0; j < hotels.size(); j++) {
				TransPortHotel hotel = hotels.get(j);
				if(null != date && hotel.getGroupId() == transPort.getGroupId() && hotel.getItemDate().compareTo(date) == 0){
					if(transPort.getHotelId() == 0){
						transPort.setHotelId(hotel.getSupplierId());
					}
					nameBuffer.append(hotel.getSupplierName());
					if(j < hotels.size() - 1 ){
						nameBuffer.append(",");
					}
				}
			}
			transPort.setHotelName(nameBuffer.toString());
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<BookingDeliveryPrice> selectDeliveryPrice(String orderIds, int page, int pageSize) {
		
		PageBean pageBean = new PageBean();
		if(page == 0){
			pageBean.setPage(1);
		}else{
			pageBean.setPage(page);
		}
		if(pageSize == 0){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(pageSize);
		}
		Map<String, Object> parameters  = new HashMap<String, Object>();
		parameters.put("orderIds", orderIds);
		pageBean.setParameter(parameters);
		List<BookingDeliveryPrice> list = doubleCarMapper.selectDeliveryPriceListPage(pageBean);
		return list;
	}
}

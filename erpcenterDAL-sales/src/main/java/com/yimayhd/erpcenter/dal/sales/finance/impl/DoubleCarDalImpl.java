package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPortHotel;
import com.yimayhd.erpcenter.dal.sales.client.car.service.DoubleCarDal;
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
				if(hotel.getGroupId() == transPort.getGroupId() && hotel.getItemDate().compareTo(date) == 0){
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

}

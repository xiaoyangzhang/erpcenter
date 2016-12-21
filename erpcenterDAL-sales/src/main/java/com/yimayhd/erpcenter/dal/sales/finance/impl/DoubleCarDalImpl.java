package com.yimayhd.erpcenter.dal.sales.finance.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.car.po.HotelMsg;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPortHotel;
import com.yimayhd.erpcenter.dal.sales.client.car.service.DoubleCarDal;
import com.yimayhd.erpcenter.dal.sales.client.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.finance.dao.DoubleCarMapper;

public class DoubleCarDalImpl implements DoubleCarDal{
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private DoubleCarMapper doubleCarMapper;
	
	@Override
	public List<TransPort> selectTransportByOrderId(int orderId) {
		List<TransPort> list = doubleCarMapper.selectTransportByOrderId(orderId);
		String groupIds = list2String(list);
		List<TransPortHotel> hotels = doubleCarMapper.selectTransPortHotelByGroupIds(groupIds);
		for (int i = 0;i < list.size();i++) {
			TransPort transPort = list.get(i);
			Date date = null;
			if(transPort.getType() == 0){//接
				date = transPort.getArrivalDate();
			}else if(transPort.getType() == 1 && null != transPort.getDepartureDate()){//送
				Calendar cal = Calendar.getInstance();
				cal.setTime(transPort.getDepartureDate());
				cal.set(Calendar.DATE, cal.get(Calendar.DATE-1));
				date = cal.getTime();
			}
			StringBuffer nameBuffer = new StringBuffer();
			for (int j = 0; j < hotels.size(); j++) {
				TransPortHotel hotel = hotels.get(j);
				if(null != date && hotel.getGroupId() == transPort.getGroupId() && hotel.getItemDate().compareTo(date) == 0){
					if(transPort.getHotelId() == 0){
						transPort.setHotelId(hotel.getSupplierId());
					}
					nameBuffer.append(hotel.getSupplierName()+",");
				}
			}
			String hotelName = nameBuffer.toString();
			if(!StringUtils.isBlank(hotelName)){
				hotelName = hotelName.substring(0, hotelName.length()-1);
			}
			transPort.setHotelName(hotelName);
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
	public List<TransPort> selectTransportByOrderIds(List<Integer> orderIds) {
		List<TransPort> list = doubleCarMapper.selectTransportByOrderIds(orderIds);
		String groupIds = list2String(list);
		List<TransPortHotel> hotels = doubleCarMapper.selectTransPortHotelByGroupIds(groupIds);
		for (int i = 0;i < list.size();i++) {
			TransPort transPort = list.get(i);
			Date date = null;
			if(transPort.getType() == 0){//接
				date = transPort.getArrivalDate();
			}else if(transPort.getType() == 1 && null != transPort.getDepartureDate()){//送
				Calendar cal = Calendar.getInstance();
				cal.setTime(transPort.getDepartureDate());
				cal.set(Calendar.DATE, cal.get(Calendar.DATE-1));
				date = cal.getTime();
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
	
	@Override
	public PageBean<BookingDeliveryPrice> selectDeliveryPrice(List<Integer> orderIds, int page, int pageSize) {
		
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
		pageBean.setResult(list);
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PageBean<GroupOrderGuest> selectOrderGuestListPage(List<Integer> orderIds,int page, int pageSize) {
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
		List<GroupOrderGuest> list = doubleCarMapper.selectGroupOrderGuestListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}


	@Override
	public List<HotelMsg> synHotelMsg(int groupId, int type,
			Date departureDate, Date arrivalDate) {
		Date date = null;
		if(type == 0 ){//接
			date = arrivalDate;
		}else if(type == 1 ){//送
			Calendar cal = Calendar.getInstance();
			cal.setTime(departureDate);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE-1));
			date = cal.getTime();
		}
		String dateStr = sdf.format(date);
		return doubleCarMapper.synHotelMsg(groupId,dateStr);
	}
}

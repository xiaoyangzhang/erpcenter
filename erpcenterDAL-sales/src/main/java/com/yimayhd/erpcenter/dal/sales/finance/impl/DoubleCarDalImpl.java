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
import com.yimayhd.erpcenter.dal.sales.constants.PickTransportTypeEnum;
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
			date = getDate(transPort, date);
			if(null == date){//如果没有传日期，酒店就不需要匹配了
				continue;
			}
			transPort = getHotelMsg(hotels, date, transPort);
		}
		return list;
	}
	
	private static Date getDate(TransPort transPort,Date date){
		if(transPort.getType() == 0){//接
			date = transPort.getArrivalDate();
		}else if(transPort.getType() == 1 && null != transPort.getDepartureDate()){//送
			Calendar cal = Calendar.getInstance();
			cal.setTime(transPort.getDepartureDate());
			cal.add(Calendar.DATE, -1);
			date = cal.getTime();
		}
		return date;
	}
	
	private static TransPort getHotelMsg(List<TransPortHotel> hotels,Date date,TransPort transPort){
		StringBuffer nameBuffer = new StringBuffer();
		for (int j = 0; j < hotels.size(); j++) {
			TransPortHotel hotel = hotels.get(j);
			if(null == hotel || null == hotel.getItemDate()){
				continue;
			}
			String queryTime = sdf.format(date);
			String itemTime = sdf.format(hotel.getItemDate());
			if(hotel.getGroupId() == transPort.getGroupId() && queryTime.equals(itemTime)){
				if(transPort.getHotelId() == 0){
					transPort.setHotelId(hotel.getSupplierId());
				}
				nameBuffer.append(hotel.getSupplierName());
				if(j < hotels.size() - 1 ){
					nameBuffer.append(",");
				}
			}
		}
		String hotelName = nameBuffer.toString();
		if(!StringUtils.isBlank(hotelName)){
			hotelName = hotelName.substring(0, hotelName.length()-1);
		}
		transPort.setHotelName(hotelName);
		return transPort;
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
			date = getDate(transPort, date);
			if(null == date){//如果没有传日期，酒店就不需要匹配了
				continue;
			}
			transPort = getHotelMsg(hotels, date, transPort);
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
		if(type == PickTransportTypeEnum.PICK.getId() ){//接
			date = arrivalDate;
		}else if(type == PickTransportTypeEnum.SEND.getId() ){//送
			Calendar cal = Calendar.getInstance();
			cal.setTime(departureDate);
			cal.add(Calendar.DATE, -1);
			date = cal.getTime();
		}else{
			return null;
		}
		String dateStr = sdf.format(date);
		return doubleCarMapper.synHotelMsg(groupId,dateStr);
	}
}

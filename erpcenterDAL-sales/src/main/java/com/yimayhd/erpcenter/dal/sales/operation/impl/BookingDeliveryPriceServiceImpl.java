package com.yimayhd.erpcenter.dal.sales.operation.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.basic.utils.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingDeliveryPriceDal;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryPriceMapper;
import org.springframework.beans.factory.annotation.Autowired;


import com.yihg.mybatis.utility.PageBean;


public class BookingDeliveryPriceServiceImpl implements BookingDeliveryPriceDal{

	@Autowired
	private BookingDeliveryPriceMapper bookingDeliveryPriceMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookingDeliveryPriceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BookingDeliveryPrice record) {
		return bookingDeliveryPriceMapper.insert(record);
	}

	@Override
	public int insertSelective(BookingDeliveryPrice record) {
		return bookingDeliveryPriceMapper.insertSelective(record);
	}

	@Override
	public BookingDeliveryPrice selectByPrimaryKey(Integer id) {
		return bookingDeliveryPriceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingDeliveryPrice record) {
		return bookingDeliveryPriceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookingDeliveryPrice record) {
		return bookingDeliveryPriceMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertBatch(List<BookingDeliveryPrice> list) {
		return bookingDeliveryPriceMapper.insertBatch(list);
	}

	@Override
	public int deleteBatch(List<BookingDeliveryPrice> list) {
		return bookingDeliveryPriceMapper.deleteBatch(list);
	}

	@Override
	public List<BookingDeliveryPrice> getPriceListByBookingId(Integer bookingId) {
		return bookingDeliveryPriceMapper.getPriceListByBookingId(bookingId);
	}

	@Override
	public void updateBatch(List<BookingDeliveryPrice> list) {
		bookingDeliveryPriceMapper.updateBatch(list);
	}

	@Override
	public int deleteByBookingId(Integer bookingId) {
		return bookingDeliveryPriceMapper.deleteByBookingId(bookingId);
	}
	
	@Override
	public String concatDetail(List<BookingDeliveryPrice> list) {
		if(list == null || list.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		BookingDeliveryPrice price = null;
		for(int i =0; i < list.size(); i++){
			
			if(i > 0){
				sb.append("<br/>");
			}
			
			price = list.get(i);
			sb.append(price.getItemName() != null ? price.getItemName() : "");
			sb.append("：");
			sb.append(NumberUtil.formatDouble(price.getUnitPrice()));
			sb.append("*");
			sb.append(NumberUtil.formatDouble(price.getNumPerson()));
			sb.append("*");
			sb.append(NumberUtil.formatDouble(price.getNumTimes()));
			sb.append("=");
			sb.append(NumberUtil.formatDouble(price.getTotalPrice()));
		}
		return sb.toString();
	}
	
	@Override
	public String concatDetailTable(List<BookingDeliveryPrice> priceList) {
		
		if(priceList == null || priceList.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='in_table'>");
			sb.append("<col width='16.6%' />");
			sb.append("<col width='16.6%' />");
			sb.append("<col width='16.6%' />");
			sb.append("<col width='16.6%' />");
			sb.append("<col width='16.6%' />");
			sb.append("<col width='16.6%' />");
			sb.append("<thead></thead>");
			sb.append("<tbody>");
			
			BookingDeliveryPrice price = null;
			for(int i =0; i < priceList.size(); i++){
				price = priceList.get(i);
				
				sb.append("<tr>");
					sb.append("<td>"+ (price.getItemName() != null ? price.getItemName() : "") +"</td>");
					sb.append("<td>"+ (price.getRemark() != null ? price.getRemark() : " ") +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getUnitPrice()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getNumPerson()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getNumTimes()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(price.getTotalPrice()) +"</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}

	@Override
	public PageBean<BookingDeliveryPrice> getSupplierPriceListPage(
			PageBean<BookingDeliveryPrice> pageBean, Integer bizId,Set<Integer> set) {
		
		List<BookingDeliveryPrice> bookingDeliveryPrices = bookingDeliveryPriceMapper.getSupplierPriceListPage(pageBean, bizId,set);
		pageBean.setResult(bookingDeliveryPrices);
		return pageBean ;
	}

	@Override
	public Map<String, Object> getSupplierPriceTotal(
			PageBean<BookingDeliveryPrice> pageBean, Integer bizId,Set<Integer> set) {
		return bookingDeliveryPriceMapper.getSupplierPriceTotal(pageBean, bizId,set);
	}

	/*@Override
	public Map<String, Object> getSupplierPriceTotalPerson(
			PageBean<BookingDeliveryPrice> pageBean, Integer bizId,
			Set<Integer> set) {
		return bookingDeliveryPriceMapper.getSupplierPriceTotalPerson(pageBean, bizId, set);
	}*/
}

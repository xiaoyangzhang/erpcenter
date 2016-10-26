package com.yimayhd.erpcenter.dal.sales.operation.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.basic.utils.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDetailDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierDetailMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;


public class BookingSupplierDetailDalImpl implements
		BookingSupplierDetailDal {

	@Autowired
	private BookingSupplierDetailMapper bookingSupplierDetailMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookingSupplierDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BookingSupplierDetail record) {
		return bookingSupplierDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(BookingSupplierDetail record) {
		return bookingSupplierDetailMapper.insertSelective(record);
	}

	@Override
	public BookingSupplierDetail selectByPrimaryKey(Integer id) {
		return bookingSupplierDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingSupplierDetail record) {
		return bookingSupplierDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookingSupplierDetail record) {
		return bookingSupplierDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BookingSupplierDetail> selectByPrimaryBookId(Integer bookId) {
		return bookingSupplierDetailMapper.selectByPrimaryBookId(bookId);
	}

	@Override
	public int deleteByBookingId(Integer bookingId) {
		return bookingSupplierDetailMapper.deleteByBookingId(bookingId);
	}

	@Override
	public List<BookingSupplierDetail> getDriversByGroupIdAndType(
			Integer groupId, Integer supplierType) {		
		return bookingSupplierDetailMapper.getListByGroupIdAndType(groupId, supplierType);
	}

	@Override
	public BookingSupplierDetail selectDriverInfoByGroupIdAndDriverId(Integer groupId, Integer driverId) {
		return bookingSupplierDetailMapper.selectDriverInfoByGroupIdAndDriverId(groupId,driverId);
	}
	
	
	/**
	 * 拼接明细字符串
	 * @param bookingSupplier
	 * @param list
	 * @return
	 */
	@Override
	public String concatDetail(Integer supplierType, String remark, List<BookingSupplierDetail> list) {
		
		if(supplierType == null || list == null){
			return "";
		}
		
		DecimalFormat df = new DecimalFormat("0.##");
		
		StringBuilder sb = new StringBuilder();
		BookingSupplierDetail detail = null;
		for (int i = 0; i < list.size(); i++) {
			
			if(i > 0){
				sb.append("<br/>");
			}
			
			detail = list.get(i);
			String type1Name = detail.getType1Name();
			String type2Name = detail.getType2Name();
			String itemDate = detail.getItemDate() != null ? DateUtils.format(detail.getItemDate(), DateUtils.FORMAT_MONTH) : "";
			String itemPrice = detail.getItemPrice() != null ? df.format(detail.getItemPrice()) : "0";
			String itemNum = detail.getItemNum() != null ? df.format(detail.getItemNum()) : "0";
			String itemNumMinus = detail.getItemNumMinus() != null ? df.format(detail.getItemNumMinus()) : "0";
			String itemBrief = detail.getItemBrief()!=null ? detail.getItemBrief():"";
			
			//景点、机票、火车票、保险、高尔夫、娱乐
			if(Constants.SCENICSPOT.equals(supplierType) || Constants.AIRTICKETAGENT.equals(supplierType) ||
					Constants.TRAINTICKETAGENT.equals(supplierType) || Constants.INSURANCE.equals(supplierType) ||
					Constants.GOLF.equals(supplierType) || Constants.ENTERTAINMENT.equals(supplierType)){
				//日期 + 项目 + 单价*（数量-免去）
				sb.append(itemDate+" ");
				if(StringUtils.isNotEmpty(type1Name)){
					sb.append(type1Name+" ");
				}
				if(!"0".equals(itemNumMinus)){
					sb.append(itemPrice+"*"+"("+itemNum+"-"+itemNumMinus+")");
				}else{
					sb.append(itemPrice+"*"+itemNum);
				}
			}
			//酒店
			else if (Constants.HOTEL.equals(supplierType)) {
				//入住日期 + 类别（房型）  单价*(数量-免去) 摘要
				sb.append(itemDate+" ");
				sb.append(type1Name);
				if(StringUtils.isNotEmpty(type2Name)){	
					sb.append("("+ type2Name +") ");
				}
				if(!"0".equals(itemNumMinus)){
					sb.append(itemPrice+"*"+"("+itemNum+"-"+itemNumMinus+")");
				}else{
					sb.append(itemPrice+"*"+itemNum);
				}
				sb.append(" "+itemBrief);
			}
			//餐厅
			else if (Constants.RESTAURANT.equals(supplierType)) {
				//用餐日期 + 餐厅（类别）  单价*(数量-免去)
				sb.append(itemDate+" ");
				sb.append(type1Name);
				if(StringUtils.isNotEmpty(type2Name)){
					sb.append("("+ type2Name +") ");
				}
				if(!"0".equals(itemNumMinus)){
					sb.append(itemPrice+"*"+"("+itemNum+"-"+itemNumMinus+")");
				}else{
					sb.append(itemPrice+"*"+itemNum);
				}
			}
			//车队
			else if (Constants.FLEET.equals(supplierType)) {
				//车型（座位数）+车牌号 司机 + 联系方式
				sb.append(type1Name);
				if(StringUtils.isNotEmpty(type2Name)){
					sb.append("("+type2Name+") ");
				}
				sb.append(detail.getCarLisence()+" "+detail.getDriverName()+" "+detail.getDriverTel());
			}
			//其他收入
			else if (Constants.OTHERINCOME.equals(supplierType)) {
				//项目  价格*数量  备注
				sb.append(type1Name+" "+itemPrice+"*"+itemNum+" "+remark);
			}
			//其他支出
			else if (Constants.OTHEROUTCOME.equals(supplierType)) {
				//项目  价格*数量  备注
				sb.append(type1Name+" "+itemPrice+"*"+itemNum+" "+remark);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 拼接明细表格
	 * @param bookingSupplier
	 * @param list
	 * @return
	 */
	@Override
	public String concatDetailTable(Integer supplierType, String remark, List<BookingSupplierDetail> list) {
		
		if(supplierType == null || list == null){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='in_table'>");
			if(Constants.HOTEL.equals(supplierType)){
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
				sb.append("<col width='16.6%' />");
			}else if(Constants.OTHERINCOME.equals(supplierType) || Constants.OTHEROUTCOME.equals(supplierType)){
				sb.append("<col width='33.3%' />");
				sb.append("<col width='33.3%' />");
				sb.append("<col width='33.3%' />");
			}else{
				sb.append("<col width='20%' />");
				sb.append("<col width='20%' />");
				sb.append("<col width='20%' />");
				sb.append("<col width='20%' />");
				sb.append("<col width='20%' />");
			}
			
			sb.append("<thead></thead>");
			sb.append("<tbody>");
			
			BookingSupplierDetail detail = null;
			for(int i =0; i < list.size(); i++){
				detail = list.get(i);
				
				String type1Name = detail.getType1Name();
				if(Constants.AIRTICKETAGENT.equals(supplierType)){
					type1Name = type1Name + "<br/>" + detail.getTicketFlight();
				}
				String type2Name = detail.getType2Name();
				String itemDate = detail.getItemDate() != null ? DateUtils.format(detail.getItemDate(), DateUtils.FORMAT_MONTH) : "";
				String itemPrice = NumberUtil.formatDouble(detail.getItemPrice());
				String itemNum = NumberUtil.formatDouble(detail.getItemNum());
				String itemNumMinus = NumberUtil.formatDouble(detail.getItemNumMinus());
				String itemBrief = detail.getItemBrief()!=null ? detail.getItemBrief():"";
				
				sb.append("<tr>");
				
				//景点、机票、火车票、保险、高尔夫、娱乐、餐厅
				if(Constants.SCENICSPOT.equals(supplierType) || Constants.AIRTICKETAGENT.equals(supplierType) ||
						Constants.TRAINTICKETAGENT.equals(supplierType) || Constants.INSURANCE.equals(supplierType) ||
						Constants.GOLF.equals(supplierType) || Constants.ENTERTAINMENT.equals(supplierType) ||
						Constants.RESTAURANT.equals(supplierType)){
					
					//日期 、项目、价格、数量、免去数
					sb.append("<td>"+ itemDate +"</td>");
					sb.append("<td>"+ type1Name);
					if(StringUtils.isNotEmpty(type2Name)){
						sb.append("("+type2Name+")");
					}
					sb.append("</td>");
					sb.append("<td>"+ itemPrice +"</td>");
					sb.append("<td>"+ itemNum +"</td>");
					sb.append("<td>"+ itemNumMinus +"</td>");
				}
				//酒店
				else if(Constants.HOTEL.equals(supplierType)){
					
					//日期 、项目、价格、数量、免去数、摘要
					sb.append("<td>"+ itemDate +"</td>");
					sb.append("<td>"+ type1Name);
					if(StringUtils.isNotEmpty(type2Name)){
						sb.append("("+type2Name+")");
					}
					sb.append("</td>");
					sb.append("<td>"+ itemPrice +"</td>");
					sb.append("<td>"+ itemNum +"</td>");
					sb.append("<td>"+ itemNumMinus +"</td>");
					sb.append("<td>"+ itemBrief +"</td>");
				}
				//车队
				else if (Constants.FLEET.equals(supplierType)) {
					//车型、座位数、车牌号、 司机、联系方式
					sb.append("<td>"+ type1Name +"</td>");
					sb.append("<td>"+ type2Name +"</td>");
					sb.append("<td>"+ detail.getCarLisence() +"</td>");
					sb.append("<td>"+ detail.getDriverName() +"</td>");
					sb.append("<td>"+ detail.getDriverTel() +"</td>");
				}
				//其他收入
				else if (Constants.OTHERINCOME.equals(supplierType) || Constants.OTHEROUTCOME.equals(supplierType)) {
					//项目、 价格、数量
					sb.append("<td>"+ type1Name +"</td>");
					sb.append("<td>"+ itemPrice +"</td>");
					sb.append("<td>"+ itemNum +"</td>");
				}
				sb.append("</tr>");
			}
			sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}

	@Override
	public List<Map<String, Integer>> getBookingIdsByType1Id(Map parameters) {
		return bookingSupplierDetailMapper.getBookingIdsByType1Id(parameters);
	}

	@Override
	public List<BookingSupplierDetail> selectBookingSupplierDetailByGroupId(
			Integer groupId) {
		return bookingSupplierDetailMapper.selectBookingSupplierDetailByGroupId(groupId);
	}
	
}

package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.text.DecimalFormat;
import java.util.List;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.dal.basic.utils.NumberUtil;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingShopDetailDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailVO;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingShopDetailBizImpl implements BookingShopDetailBiz{

	@Autowired
	private BookingShopDetailDal bookingShopDetailDal;

	@Override
	public List<BookingShopDetail> getShopDetailListByBookingId(Integer bId) {
		
		return bookingShopDetailDal.getShopDetailListByBookingId(bId);
	}
	
	public BookingShopDetail getShopDetailById(Integer id) {
			
			return bookingShopDetailDal.getShopDetailById(id);
		}
	

	@Override
	public int save(BookingShopDetail shopDetail) {
		return bookingShopDetailDal.save(shopDetail) ;
		
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookingShopDetailDal.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingShopDetail record) {
		return bookingShopDetailDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByBookingId(Integer bookingId) {
		bookingShopDetailDal.deleteByBookingId(bookingId);
	}
	
	@Override
	public String concatDetail(List<BookingShopDetail> list) {
		if(list == null || list.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		BookingShopDetail detail = null;
		for(int i =0; i < list.size(); i++){
			
			if(i > 0){
				sb.append("<br/>");
			}
			
			detail = list.get(i);
			sb.append(detail.getGoodsName());
			sb.append(",");
			if(detail.getRepayVal() != null){
				sb.append(detail.getRepayVal().doubleValue() +"%");
			}else{
				sb.append("0%");
			}
			sb.append(",");
			if(detail.getRepayTotal() != null){
				sb.append(detail.getRepayTotal().doubleValue());
			}
			
		}
		return sb.toString();
	}
	
	@Override
	public String concatDetailTable(List<BookingShopDetail> list) {
		if(list == null || list.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='in_table'>");
			sb.append("<col width='33%' />");
			sb.append("<col width='33%' />");
			sb.append("<col width='33%' />");
			sb.append("<thead></thead>");
			sb.append("<tbody>");
			
			BookingShopDetail detail = null;
			for(int i =0; i < list.size(); i++){
				detail = list.get(i);
				
				sb.append("<tr>");
					sb.append("<td>"+ (detail.getGoodsName() != null ? detail.getGoodsName() : "") +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(detail.getBuyTotal()) +"</td>");
					sb.append("<td>"+ NumberUtil.formatDouble(detail.getRepayTotal()) +"</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}

	@Override
	public void updateBookingShopList(BookingShopDetailVO detailVO) {
		bookingShopDetailDal.updateBookingShopList(detailVO);
	}
}

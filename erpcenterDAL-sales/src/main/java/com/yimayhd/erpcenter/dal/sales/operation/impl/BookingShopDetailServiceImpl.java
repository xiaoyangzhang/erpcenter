package com.yihg.operation.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.basic.util.NumberUtil;
import com.yihg.images.util.DateUtils;
import com.yihg.operation.api.BookingShopDetailService;
import com.yihg.operation.dao.BookingShopDetailDeployMapper;
import com.yihg.operation.dao.BookingShopDetailMapper;
import com.yihg.operation.dao.BookingShopMapper;
import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.vo.BookingShopDetailVO;
import com.yihg.tj.dao.TJMapper;
import com.yihg.tj.po.TJShopProject;

public class BookingShopDetailServiceImpl implements BookingShopDetailService{

	@Autowired
	private BookingShopDetailMapper shopDetailMapper;
	@Autowired
	private BookingShopDetailDeployMapper deployMapper;
	@Autowired
	private BookingShopMapper shopMapper;
	@Autowired
	private TJMapper tjMapper;
	
	@Override
	public List<BookingShopDetail> getShopDetailListByBookingId(Integer bId) {
		
		return shopDetailMapper.getShopDetailListByBookingId(bId);
	}
	
	public BookingShopDetail getShopDetailById(Integer id) {
			
			return shopDetailMapper.selectByPrimaryKey(id);
		}
	

	@Override
	public int save(BookingShopDetail shopDetail) {
		int i = 0;
		if(null!=shopDetail.getId()){
			 i = shopDetailMapper.updateByPrimaryKeySelective(shopDetail);
		}else{
		
			shopDetail.setCreateTime(System.currentTimeMillis());
			shopDetailMapper.insertSelective(shopDetail);
			i = shopDetail.getId();
		}
		//shopDetail.getBookingId();
		if(shopDetail.getType() != null){
			if(shopDetail.getType().equals((byte)1)){
				shopMapper.updatetotalFace(shopDetail.getBookingId());
			}
		}
		return i ;
		
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
//		BookingShopDetail bookingShopDetail = shopDetailMapper.selectByPrimaryKey(id);
//		TJShopProject tjShopProject = new TJShopProject();
//		tjShopProject.setDetailId(bookingShopDetail.getId());
//		tjShopProject.setGoodsId(bookingShopDetail.getGoodsId());
//		tjShopProject.setGoodsName(bookingShopDetail.getGoodsName());
//		tjShopProject.setBuyTotal(bookingShopDetail.getBuyTotal());
//		tjShopProject.setRepayTotal(bookingShopDetail.getRepayTotal());
//		tjShopProject.setRepayVal(bookingShopDetail.getRepayVal());
	
		deployMapper.deleteByPrimaryKey(id);
		int result = shopDetailMapper.deleteByPrimaryKey(id);
		if(result==1){
//			tjMapper.updateTjShopProjectByDetailId(tjShopProject);
			tjMapper.deleteByDetailId(id);
		}
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(BookingShopDetail record) {
		return shopDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByBookingId(Integer bookingId) {
		List<BookingShopDetail> lists = shopDetailMapper.getShopDetailListByBookingId(bookingId);
		shopDetailMapper.deleteByBookingId(bookingId);
		for (BookingShopDetail bookingShopDetail : lists) {
//			TJShopProject tjShopProject = new TJShopProject();
//			tjShopProject.setDetailId(bookingShopDetail.getId());
//			tjShopProject.setGoodsId(bookingShopDetail.getGoodsId());
//			tjShopProject.setGoodsName(bookingShopDetail.getGoodsName());
//			tjShopProject.setBuyTotal(bookingShopDetail.getBuyTotal());
//			tjShopProject.setRepayTotal(bookingShopDetail.getRepayTotal());
//			tjShopProject.setRepayVal(bookingShopDetail.getRepayVal());
//			tjMapper.updateTjShopProjectByDetailId(tjShopProject);
			tjMapper.deleteByDetailId(bookingShopDetail.getId());
		}
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
		List<BookingShopDetail> details = detailVO.getDetail();
		if (details!=null && details.size()>0) {
			for (BookingShopDetail detail : details) {
//				BookingShop shop=new BookingShop();
//				shop.setId(detail.getBookingId());
				if (null == detail.getId()) {
					shopDetailMapper.insertSelective(detail);
				}
				else {
					shopDetailMapper.updateByPrimaryKeySelective(detail);
				}
			}
		}
	}
}

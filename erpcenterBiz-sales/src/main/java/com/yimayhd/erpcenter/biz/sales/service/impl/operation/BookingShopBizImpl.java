package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDet;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopSelect;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingShopDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.QueryGuideShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;


public class BookingShopBizImpl implements BookingShopBiz{

	@Autowired
	private BookingShopDal bookingShopDal;

	@Override
	public List<BookingShop> getShopListByGroupId(Integer groupId) {
		
		return bookingShopDal.getShopListByGroupId(groupId);
	}

	@Override
	public int save(BookingShop shop) {
		bookingShopDal.save(shop);
		return shop.getId() ;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookingShopDal.deleteByPrimaryKey(id);
	}

	@Override
	public BookingShop selectByPrimaryKey(Integer id) {
		return  bookingShopDal.selectByPrimaryKey(id);
	}

	@Override
	public Integer getSelectCountByGruopId(Integer groupId) {
		return bookingShopDal.getSelectCountByGruopId(groupId);
		
	}

	@Override
	public int getBookingCountByTime() {
		return bookingShopDal.getBookingCountByTime();
	}

	@Override
	public List<BookingShopDet> getShopListByGroupIdAndShopDate(Integer groupId,
			String shopDate) {
		return bookingShopDal.getShopListByGroupIdAndShopDate(groupId, shopDate);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingShop record) {
		return bookingShopDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageBean getGuideShop(PageBean pageBean,Set<Integer> set) {
		return bookingShopDal.getGuideShop(pageBean,set);
	}

	@Override
	public BookingShopSelect getShopSelect(Integer groupId) {
		return bookingShopDal.getShopSelect(groupId);
	}

	@Override
	public PageBean getshopInfoDetail(PageBean pageBean) {
		return bookingShopDal.getshopInfoDetail(pageBean);
	}

	@Override
	public int updatetotalFace(int bId) {
		return bookingShopDal.updatetotalFace(bId);
	}

	@Override
	public PageBean selectShopListPage(PageBean pageBean, Integer bizId) {
		return bookingShopDal.selectShopListPage(pageBean,bizId);
	}

	@Override
	public PageBean selectShopVerifyListPage(PageBean pageBean, Integer bizId) {
		return bookingShopDal.selectShopVerifyListPage(pageBean, bizId);
	}
	
	@Override
	public Map<String,Object> selectShopVerifySum(PageBean pageBean, Integer bizId) {
		return bookingShopDal.selectShopVerifySum(pageBean, bizId);
	}

	@Override
	public List<Map<String, Object>> getBookingShops(Integer groupId,
			Integer bizId) {
		return bookingShopDal.getBookingShops(groupId, bizId);
	}

	@Override
	@Transactional
	public void saveShopAndDetail(String bizCode,Integer userId,String userName,List<BookingShopDetail> bShopDetails,BookingShop shop) {
		bookingShopDal.saveShopAndDetail(bizCode,userId,userName,bShopDetails,shop);
	}

	@Override
	public PageBean selectShopTJListPage(PageBean pageBean) {
		return bookingShopDal.selectShopTJListPage(pageBean);
	}

	@Override
	public Integer existBookingShop(Integer supplierId) {
		return bookingShopDal.existBookingShop(supplierId);
	}


}

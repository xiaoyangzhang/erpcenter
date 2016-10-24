package com.yimayhd.erpcenter.dal.sales.client.operation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.po.BookingShop;
import com.yihg.operation.po.BookingShopDet;
import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.po.BookingShopSelect;

public interface BookingShopService {
	
	int updateByPrimaryKeySelective(BookingShop record);

	List<BookingShop> getShopListByGroupId(Integer groupId);

	List<BookingShopDet> getShopListByGroupIdAndShopDate(Integer groupId,
			String shopDate);

	int save(BookingShop record);

	int deleteByPrimaryKey(Integer id);

	BookingShop selectByPrimaryKey(Integer id);

	Integer getSelectCountByGruopId(Integer groupId);

	int getBookingCountByTime();
	PageBean getGuideShop(PageBean pageBean,Set<Integer> set);

	BookingShopSelect getShopSelect(Integer groupId);

	PageBean getshopInfoDetail(PageBean pageBean);
	
	int updatetotalFace(int bId);
	/**
	 * 查询购物统计列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean selectShopListPage(PageBean pageBean, Integer bizId);
	/**
	 * 查询购物审核列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean selectShopVerifyListPage(PageBean pageBean, Integer bizId);
	Map<String,Object> selectShopVerifySum(PageBean pageBean, Integer bizId);
	List<Map<String, Object>> getBookingShops(Integer groupId,Integer bizId);

	void saveShopAndDetail(String myBizCode, Integer curUserId, String name,
			List<BookingShopDetail> bShopDetails, BookingShop shop);

	PageBean selectShopTJListPage(PageBean pageBean);

	Integer existBookingShop(Integer supplierId);

}

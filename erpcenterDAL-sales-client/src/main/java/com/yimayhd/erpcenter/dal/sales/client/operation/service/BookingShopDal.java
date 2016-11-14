package com.yimayhd.erpcenter.dal.sales.client.operation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDet;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopSelect;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.ShopGroupOrderOthersPO;


public interface BookingShopDal {
	
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
	
	/**
	 * 根据订单id查询剩下的solr所需信息
	 * @author liyong
	 * @param id 订单id
	 * @return
	 */
	ShopGroupOrderOthersPO selectShopGroupOrderOthersByOrderId(Integer id);

}

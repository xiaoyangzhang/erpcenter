package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDetailDeployDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopListDTO;
import com.yimayhd.erpcenter.facade.sales.result.ToFactShopResult;

/**
 * 
* @ClassName: BookingShopFacade 
* @Description: 
* @author wangjun
* @date 2016年10月25日 下午4:32:17 
*
 */
public interface BookingShopFacade {
	/**
	 * 客人购物录入查询
	 * @param bookingShopListDTO
	 * @return
	 * @author wangjun
	 */
	PageBean bookingShopList(BookingShopListDTO bookingShopListDTO);
	/**
	 * 客人购物录入-编辑
	 * @param bookingShopDTO
	 * @return
	 * @author wangjun
	 */
	List<Map<String, Object>> toEditShop(BookingShopDTO bookingShopDTO);
	/**
	 * 客人购物录入-编辑保存
	 * @param shopDetails
	 * @author wangjun
	 */
	void saveShopDetails(String shopDetails);
	
	/**
	 * 购物单列表
	 * @param groupId
	 * @return
	 * @author wangjun
	 */
	List<BookingShop> shopDetailList(int groupId);
	
	/**
	 * 购物单编辑页面
	 * @param bookingShopDTO
	 * @return
	 * @author wangjun
	 */
	ToFactShopResult toFactShop(BookingShopDTO bookingShopDTO);
	
	/**
	 * 购物单编辑-保存
	 * @return
	 * @author wangjun
	 */
	int saveShopDetail(BookingShopDetailDeployDTO bookingShopDetailDeployDTO);
	/**
	 * 删除购物单
	 * @param bookingId
	 * @return
	 * @author wangjun
	 */
	int delBookingShop(int bookingId);
	
}

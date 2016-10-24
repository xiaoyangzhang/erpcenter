package com.yimayhd.erpcenter.dal.sales.client.operation.service;

import java.util.List;

import com.yihg.operation.po.BookingDeliveryPrice;
import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.vo.BookingShopDetailVO;

public interface BookingShopDetailService {

	int updateByPrimaryKeySelective(BookingShopDetail record);

	List<BookingShopDetail> getShopDetailListByBookingId(Integer bId);

	int save(BookingShopDetail shopDetail);

	int deleteByPrimaryKey(Integer id);

	BookingShopDetail getShopDetailById(Integer id);
	
	void deleteByBookingId(Integer bookingId);
	
	/**
	 * 拼接购物店详字符串
	 * @param list
	 * @return
	 */
	String concatDetail(List<BookingShopDetail> list);
	
	/**
	 * 拼接购物明细表格
	 * @param list
	 * @return
	 */
	String concatDetailTable(List<BookingShopDetail> list);
	
	void updateBookingShopList(BookingShopDetailVO detailVO);
}

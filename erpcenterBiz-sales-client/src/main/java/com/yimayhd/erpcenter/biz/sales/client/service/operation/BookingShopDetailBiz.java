package com.yimayhd.erpcenter.biz.sales.client.service.operation;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailVO;

import java.util.List;


public interface BookingShopDetailBiz {

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

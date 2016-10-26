package com.yimayhd.erpcenter.biz.sales.client.service.operation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;


public interface BookingDeliveryPriceBiz {
	int deleteByPrimaryKey(Integer id);

    int insert(BookingDeliveryPrice record);

    int insertSelective(BookingDeliveryPrice record);

    BookingDeliveryPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingDeliveryPrice record);

    int updateByPrimaryKey(BookingDeliveryPrice record);
    
    int insertBatch(List<BookingDeliveryPrice> list);
    
    int deleteBatch(List<BookingDeliveryPrice> list);

	List<BookingDeliveryPrice> getPriceListByBookingId(Integer bookingId);

	void updateBatch(List<BookingDeliveryPrice> list);
	
	int deleteByBookingId(Integer bookingId);
	
	/**
	 * 将订单价格明细拼接成字符串
	 * @param priceList
	 * @return
	 */
	String concatDetail(List<BookingDeliveryPrice> priceList);
	
	/**
	 * 将订单价格明细拼接成表格
	 * @param priceList
	 * @return
	 */
	String concatDetailTable(List<BookingDeliveryPrice> priceList);
	
	PageBean<BookingDeliveryPrice> getSupplierPriceListPage(PageBean<BookingDeliveryPrice> pageBean,Integer bizId,Set<Integer> set);
	
	Map<String, Object> getSupplierPriceTotal(PageBean<BookingDeliveryPrice> pageBean,Integer bizId,Set<Integer> set) ;
	//Map<String, Object> getSupplierPriceTotalPerson(PageBean<BookingDeliveryPrice> pageBean,Integer bizId,Set<Integer> set) ;
}

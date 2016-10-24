package com.yihg.operation.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.alibaba.dubbo.config.support.Parameter;
import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.po.BookingDeliveryPrice;

public interface BookingDeliveryPriceMapper {
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
	
	List<BookingDeliveryPrice> getSupplierPriceListPage(@Param("page")PageBean<BookingDeliveryPrice> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	Map<String, Object> getSupplierPriceTotal(@Param("page")PageBean<BookingDeliveryPrice> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set) ;
	//Map<String, Object> getSupplierPriceTotalPerson(@Param("page")PageBean<BookingDeliveryPrice> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set) ;
}
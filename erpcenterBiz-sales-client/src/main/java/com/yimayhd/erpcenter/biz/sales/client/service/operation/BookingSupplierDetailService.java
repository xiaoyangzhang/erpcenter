package com.yimayhd.erpcenter.biz.sales.client.service.operation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.po.BookingSupplier;
import com.yihg.operation.po.BookingSupplierDetail;

public interface BookingSupplierDetailService {
	int deleteByPrimaryKey(Integer id);
	int deleteByBookingId(Integer bookingId);

	int insert(BookingSupplierDetail record);

	int insertSelective(BookingSupplierDetail record);

	BookingSupplierDetail selectByPrimaryKey(Integer id);
	 
	List<BookingSupplierDetail> selectByPrimaryBookId(Integer bookId);

	int updateByPrimaryKeySelective(BookingSupplierDetail record);

	int updateByPrimaryKey(BookingSupplierDetail record);
	List<BookingSupplierDetail> getDriversByGroupIdAndType(Integer groupId,Integer supplierType);

	BookingSupplierDetail selectDriverInfoByGroupIdAndDriverId(Integer groupId, Integer driverId);
	 
	 /**
	  * 拼接明细字符串
	  * @param bookingSupplier
	  * @param list
	  * @return
	  */
	String concatDetail(Integer supplierType, String remark, List<BookingSupplierDetail> list);
	
	/**
	  * 拼接明细表格
	  * @param bookingSupplier
	  * @param list
	  * @return
	  */
	String concatDetailTable(Integer supplierType, String remark, List<BookingSupplierDetail> list);
	// List<BookingSupplierDetail> getDetailsByTypeAndBizId(Integer supplierType,Integer bizId);
	 List<Map<String, Integer>> getBookingIdsByType1Id(Map parameters);
	
	 /**
	     * 根据团号查询团下安排的车信息
	     * @param groupId
	     * @return
	     */
	List<BookingSupplierDetail> selectBookingSupplierDetailByGroupId(Integer groupId);
}

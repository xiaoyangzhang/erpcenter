package com.yimayhd.erpcenter.dal.sales.operation.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import org.apache.ibatis.annotations.Param;


public interface BookingSupplierDetailMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByBookingId(@Param("bookId")Integer bookingId);

    int insert(BookingSupplierDetail record);

    int insertSelective(BookingSupplierDetail record);

    BookingSupplierDetail selectByPrimaryKey(Integer id);
    
	List<BookingSupplierDetail> selectByPrimaryBookId(@Param("bookId")Integer bookId);

    int updateByPrimaryKeySelective(BookingSupplierDetail record);

    int updateByPrimaryKey(BookingSupplierDetail record);
//    BigDecimal getBookingPriceSumByGroupIdAndSupplierType(@Param("groupId")Integer groupId,@Param("supplierType")Integer supplierType);
//    Integer getBookingCountByGroupIdAndSupplierType(@Param("groupId")Integer groupId,@Param("supplierType")Integer supplierType);
    BigDecimal getBookingPriceSumByBookingId(Integer bookingId);
    //根据bookingId计算订单详情表中各订单详情的金额之和
    int updateBookingTotalByBookingId(Integer bookingId);
    
    List<BookingSupplierDetail> getListByGroupIdAndType(@Param("groupId")Integer groupId,@Param("type")Integer type);

    BookingSupplierDetail selectDriverInfoByGroupIdAndDriverId(@Param("groupId")Integer groupId,@Param("driverId")Integer driverId);
    //List<BookingSupplierDetail> getBookingSupplierDetailsByTypeAndBizId(@Param("supplierType")Integer supplierType,@Param("bizId")Integer bizId);
    List<Map<String, Integer>> getBookingIdsByType1Id(@Param("parameter")Map parameters);
    
    /**
     * 根据团号查询团下安排的车信息
     * @param groupId
     * @return
     */
    List<BookingSupplierDetail> selectBookingSupplierDetailByGroupId(@Param("groupId")Integer groupId);
}
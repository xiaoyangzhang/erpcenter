package com.yihg.finance.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.finance.po.CheckSheetFinance;
import com.yihg.finance.po.FinanceGuide;
import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.po.BookingGuide;
import com.yihg.operation.po.BookingShop;

public interface FinanceGuideMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByBookingIdLink(Integer bookingIdLink);
    
    int deleteByBookingId(Integer bookingId);

    int insert(FinanceGuide record);
    
    Integer selectCountByBookingId(@Param("bookingId")Integer bookingId);

    int insertSelective(FinanceGuide record);

    FinanceGuide selectByPrimaryKey(Integer id);
    
    List<FinanceGuide> selectListByGroupIdAndBookingId(@Param("groupId")Integer groupId,@Param("bookingId")Integer bookingId);
    
	List<FinanceGuide> selectSelectiveListByGroupIdAndBookingId(@Param("groupId")Integer groupId,@Param("bookingId")Integer bookingId);
	
    List<CheckSheetFinance> selectListByGroupIdAndBookingIdAndType(@Param("groupId")Integer groupId,@Param("bookingId")Integer bookingId,@Param("supplierType")Integer supplierType);
    
    int updateByPrimaryKeySelective(FinanceGuide record);

    int updateByPrimaryKey(FinanceGuide record);
    
    List<FinanceGuide> selectByProperty(FinanceGuide financeGuide);
    
    List<FinanceGuide> selectByPropertyAndStr(@Param("bookingId")Integer bookingId,@Param("checkedArr")String checkedArr);
    //查询汇总金额
    BigDecimal selectSumTotalGroupIdAndBookingId(@Param("groupId")Integer groupId,@Param("bookingId")Integer bookingId,@Param("type")Integer type);
    
    BigDecimal selectSumTotalPay(@Param("groupId")Integer groupId,@Param("bookingId")Integer bookingId,@Param("type")Integer type);
    
    BigDecimal selectSumTotalPay2(@Param("page") PageBean pageBean, @Param("type")Integer type);
    /**
     * 获取导游列表
     * @param bizId
     * @param name
     * @return
     */
	List<Map<String, String>> getGuideNameList(@Param("bizId")Integer bizId,@Param("name")String name);

	List<BookingGuide> querySettleListPage(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);
	
	List<BookingGuide> querySettleList(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);
	
	BigDecimal getSumTotal(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);
	
	FinanceGuide selectByBookingIdLink(Integer bookingIdLink);
	//BigDecimal selectTotalByBookingIdLinkAndType(@Param("bookingIdLink")Integer bookingId,@Param("type")Integer type);
	
	/**
	 * 查询导游报账未审核的订单数
	 * @param bookingId
	 * @return
	 */
	int selectUnauditedOrderCount(Integer bookingId);
}
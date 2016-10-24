package com.yihg.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.finance.po.FinancePayDetail;

public interface FinancePayDetailCommissionDeductionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinancePayDetail record);

    int insertSelective(FinancePayDetail record);

    FinancePayDetail selectByPrimaryKey(Integer id);
    
    FinancePayDetail selectByPayIdAndLocOrderId(@Param("payId")Integer payId, @Param("locOrderId")Integer locOrderId);
    
    List<FinancePayDetail> selectByPayId(@Param("payId")Integer payId);
    
    int deleteByLocOrderIdAndPayId(@Param("locOrderId")Integer locOrderId, @Param("payId")Integer payId);
    
    int deleteByPayId(Integer payId);
    
    int update(FinancePayDetail detail);
    
    List<Map<String, Object>> selectGuidePayDetailsByPayId(@Param("payId")Integer payId);
    
    List<Map<String, Object>> selectCommPayDetailsByPayId(@Param("payId")Integer payId);
    
    List<Map<String, Object>> selectDetailByLocOrderId(@Param("locOrderId")Integer locOrderId);
}
package com.yihg.finance.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.finance.po.FinanceCommission;
import com.yihg.mybatis.utility.PageBean;

public interface FinanceCommissionDeductionMapper {
	
	/**
	 * 查询团导游佣金列表
	 */
	List<FinanceCommission> selectByGroupId(Integer groupId);
	
	/**
	 * 查询团的佣金金额列表
	 * @param groupId
	 * @return
	 */
	Map<String, Object> selectTotalByGroupId(Integer groupId);
	
	/**
	 * 查询导游佣金列表
	 * @param ids
	 * @return
	 */
	List<FinanceCommission> selectByPrimaryKeys(@Param("ids")String ids);
	
	/**
	 * 查询未付款的导游佣金列表
	 * @param ids
	 * @return
	 */
	List<FinanceCommission> selectUnPayCommByPrimaryKeys(@Param("ids")String ids);
	
	/**
	 * 删除团导游佣金
	 * @param bizId
	 * @param id
	 * @param guideId
	 */
	void deleteByPrimaryKey(@Param("bizId")Integer bizId, @Param("id")Integer id);
	
	/**
	 * 删除团的佣金
	 * @param bizId
	 * @param groupId
	 */
	void deleteByGroupId(@Param("bizId")Integer bizId, @Param("groupId")Integer groupId);
	
	/**
	 * 添加团导游佣金
	 * @param com
	 */
	int insert(FinanceCommission com);

	List<FinanceCommission> getFinanceCommisionByGroupIdAndGuideId(@Param("bizId")Integer bizId, @Param("groupId")Integer groupId, @Param("guideId")Integer guideId);
	
	List<Map<String, Object>> getCommisionStatsListPage(@Param("page") PageBean pageBean);
	
	List<FinanceCommission> getCommisions(@Param("page") PageBean pageBean);
	
	/**
	 * 修改导游佣金
	 * @param com
	 * @return
	 */
	int update(FinanceCommission com);

	Map<String, Object> getCommisionsTotal(@Param("page") PageBean pageBean);
	
	/**
	 * 查询导游带团的佣金和
	 * @param groupId
	 * @param guideId
	 * @return
	 */
	Integer getCommisionTotalSum(@Param("groupId")Integer groupId, @Param("guideId")Integer guideId);
	
	/**
	 * 查询导游带团的 佣金总和、已付款总和
	 * @param groupId
	 * @param guideId
	 * @return
	 */
	Map<String, Object> getCommisionTotalSumAndTotalCashSum(@Param("groupId")Integer groupId, @Param("guideId")Integer guideId);
	
	/**
	 * 查询导游佣金总计
	 * @param groupId
	 * @param guideId
	 * @return
	 */
	Map<String, Object> getAllTotalSumAndTotalCashSum(@Param("page") PageBean pageBean);
}
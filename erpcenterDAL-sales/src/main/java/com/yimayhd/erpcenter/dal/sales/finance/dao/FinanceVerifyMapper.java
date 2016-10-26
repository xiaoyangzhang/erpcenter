package com.yimayhd.erpcenter.dal.sales.finance.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerifyDetail;
public interface FinanceVerifyMapper {
	/**
	 * 修改对账单状态
	 * @param id
	 */
	void changeStatus(String id);
	
	/**
	 * 新增对账单
	 * @param verify
	 */
	Integer insert(FinanceVerify verify);
	
	/**
	 * 获取新增对账单插入的主键
	 * @param verify
	 */
	Integer selectInsertKey();
	
	/**
	 * 得到供应商列表
	 * @param bizId
	 * @param supplierType
	 * @param supplierName
	 * @return
	 */
	List<Map<String, String>> getSupplierNameList(@Param("bizId")Integer bizId, @Param("supplierType")Integer supplierType, @Param("supplierName")String supplierName);
	
	/**
	 * 得到供应商个数
	 * @param bizId
	 * @param supplierName
	 * @return
	 */
	Integer getSupplierNameCount(@Param("bizId")Integer bizId, @Param("supplierType")Integer supplierType, @Param("supplierName")String supplierName);
	
	/**
	 * 得到对账单最大的序号
	 * @param bizId
	 * @return
	 */
	Integer getMaxSort(Integer bizId);
	
	/**
	 * 查询对账单详情
	 * @param bizId
	 * @param verifyId
	 * @return
	 */
	List<Map<String, Object>> selectVerifyDetailPage(@Param("bizId") Integer bizId,@Param("verifyId") String verifyId,
			@Param("supplierType") Integer supplierType, @Param("set")Set<Integer> set);
	
	List<FinanceVerifyDetail> selectVerifyDetailSum(@Param("bizId") Integer bizId,@Param("verifyId") String verifyId,@Param("supplierType") Integer supplierType);
	
	
	/**
	 * 删除对账单
	 * @param bizId
	 * @param id
	 */
	void deleteVerify(@Param("bizId") Integer bizId, @Param("id") Integer id);
	
	/**
	 * 删除对账单详情
	 * @param bizId
	 * @param verifyId
	 */
	void deleteVerifyDetail(@Param("bizId") Integer bizId, @Param("verifyId") Integer verifyId);
	
	/**
	 * 根据对账单详情ID删除
	 * @param bizId
	 * @param verifyId
	 */
	void deleteVerifyDetailById(@Param("bizId") Integer bizId, @Param("id") Integer id);
	
	/**
	 * 根据ID查询对账单
	 * @param bizId
	 * @param verifyId
	 * @return
	 */
	FinanceVerify selectVerify(@Param("bizId")Integer bizId, @Param("verifyId")String verifyId);
	
	/**
	 * 添加对账单详情
	 * @param verify
	 */
	void insertDetail(FinanceVerifyDetail detail);
	
	
	/**
	 * 修改对账单状态
	 */
	void updateVerifyState(@Param("bizId")Integer bizId, @Param("id")Integer id, @Param("verifyState")Integer verifyState);
	
	/**
	 * 修改对账单
	 * @param verify
	 */
	void updateVerify(@Param("verify")FinanceVerify verify);
	
	/**
	 * 修改对账单统计字段
	 * @param verify
	 */
	void updateVerifyTotal(@Param("verify")FinanceVerify verify);
	
	/**
	 * 查询可添加到对账单的订单
	 * @return
	 */
	List<Map<String, Object>> selectVerifyDetailOrderListPage(PageBean pageBean);
}
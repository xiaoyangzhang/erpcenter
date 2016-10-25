package com.yimayhd.erpcenter.biz.sales.client.service.finance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerifyDetail;

/**
 * 对账管理
 */
public interface FinanceVerifyBiz{

	/**
	 * 修改对账单状态
	 * @param id
	 */
	void changeStatus(String id);
	/**
	 * 查询对账单详情
	 * @param curBizId
	 * @param verifyId
	 * @return
	 */
	List<Map<String, Object>> selectVerifyDetailPage(Integer bizId,
			String verifyId,Integer supplierType, Set<Integer> set);
	
	/**
	 * 新增对账单
	 * @param verify
	 */
	Integer insert(FinanceVerify verify, String bizCode) throws ClientException;

	
	/**
	 * 得到对账单最大的序号
	 * @param bizId
	 */
	Integer getMaxSort(Integer bizId);
	
	/**
	 * 得到供应商名称列表
	 * @param bizId
	 * @param supplierType
	 * @param supplierName
	 * @return
	 */
	List<Map<String, String>> getSupplierNameList(Integer bizId, Integer supplierType, String supplierName);
	
	
	/**
	 * 生成对账单号
	 * @param bizCode
	 * @param supplierType
	 * @return
	 */
	String generateVerifyCode(String bizCode, Integer supplierType, Integer sort);
	
	List<FinanceVerifyDetail> selectVerifyDetailSum(Integer bizId, String verifyId,Integer supplierType);
	
	/**
	 * 删除对账单
	 * @param bizId
	 * @param id
	 */
	void deleteVerify(Integer bizId, Integer id);
	
	/**
	 * 根据ID查询对账单
	 * @param bizId
	 * @param verifyId
	 * @return
	 */
	FinanceVerify selectVerify(Integer bizId, String verifyId);
	
	/**
	 * 添加对账单详情
	 * @param verify
	 */
	void insertDetail(Integer bizId, Integer supplierType, Integer verifyId, String ids);
	
	/**
	 * 更新对账单详情
	 * @param verify
	 */
	void updateDetail(Integer bizId, Integer supplierType, Integer verifyId, String records);
	
	/**
	 * 删除对账单和对账单详情
	 * @param id
	 */
	void deleteVerifyAndDetail(Integer bizId, Integer id);
	
	/**
	 * 删除对账单详情数据，并且修改对账单表的金额
	 * @param id
	 * @param total
	 * @param tataldCash
	 * @param totalAdjust
	 */
	void deleteVerifyDetail(Integer bizId, Integer verifyId, Integer detailId, 
			BigDecimal total, BigDecimal totalCash, BigDecimal totalAdjust);
	
	/**
	 * 查询可添加到对账单的订单
	 * @param params
	 * @return
	 */
	PageBean selectVerifyDetailOrderListPage(PageBean pageBean);

}

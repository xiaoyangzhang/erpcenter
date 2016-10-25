package com.yimayhd.erpcenter.biz.sales.service.impl.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceVerifyBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerifyDetail;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceVerifyDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;

/**
 * 对账管理
 */
public class FinanceVerifyBizImpl implements FinanceVerifyBiz {

	@Autowired
	private FinanceVerifyDal financeVerifyDal;
	
	/**
	 * 修改对账单状态
	 */
	@Override
	public void changeStatus(String id) {
		financeVerifyDal.changeStatus(id);
		
	}
	@Override
	public List<Map<String, Object>> selectVerifyDetailPage(Integer bizId,
			String verifyId,Integer supplierType, Set<Integer> set) {
		return financeVerifyDal.selectVerifyDetailPage(bizId, verifyId, supplierType, set);
	}
	@Override
	public List<FinanceVerifyDetail> selectVerifyDetailSum(Integer bizId,
			String verifyId,Integer supplierType) {
		return financeVerifyDal.selectVerifyDetailSum(bizId, verifyId, supplierType);
	}
	
	/**
	 * 新增对账单
	 */
	@Transactional
	@Override
	public Integer insert(FinanceVerify verify, String bizCode) throws ClientException {
		
		return financeVerifyDal.insert(verify, bizCode);
	}
	
	@Override
	public Integer getMaxSort(Integer bizId) {
		return financeVerifyDal.getMaxSort(bizId);
	}
	

	@Override
	public List<Map<String, String>> getSupplierNameList(Integer bizId, Integer supplierType, String supplierName) {
		return financeVerifyDal.getSupplierNameList(bizId, supplierType, supplierName);
	}
	
	/**
	 * 生成对账单号
	 * 对账单编号规则：
	 * YM-CT20151008-1
	 * 企业编码-类型 日期-递增编号
	 */
	@Override
	public String generateVerifyCode(String bizCode, Integer supplierType, Integer sort) {
		
		return financeVerifyDal.generateVerifyCode(bizCode, supplierType, sort);
	}
	@Override
	public void deleteVerify(Integer bizId, Integer id) {
		financeVerifyDal.deleteVerify(bizId, id);
	}
	
	@Override
	public FinanceVerify selectVerify(Integer bizId, String verifyId) {
		return financeVerifyDal.selectVerify(bizId, verifyId);
	}
	
	/**
	 * 添加对账单详情
	 */
	@Transactional
	@Override
	public void insertDetail(Integer bizId, Integer supplierType, Integer verifyId, String ids){
		
		financeVerifyDal.insertDetail(bizId, supplierType, verifyId, ids);
		
	}
	
	/**
	 * 更新对账单详情
	 * records
	 * [
	 * 	{orderId:1212,remark:asdf,total_adjust},
	 * 	{orderId:1212,remark:asdf,total_adjust}
	 * ]
	 */
	@Transactional
	@Override
	public void updateDetail(Integer bizId, Integer supplierType, Integer verifyId, String records){
		
		financeVerifyDal.updateDetail(bizId, supplierType, verifyId, records);
		
	}
	
	@Transactional
	@Override
	public void deleteVerifyAndDetail(Integer bizId, Integer id) {
		financeVerifyDal.deleteVerifyAndDetail(bizId, id);
	}
	
	@Transactional
	@Override
	public void deleteVerifyDetail(Integer bizId, Integer verifyId, Integer detailId, 
			BigDecimal total, BigDecimal totalCash, BigDecimal totalAdjust) {
		
		financeVerifyDal.deleteVerifyDetail(bizId, verifyId, detailId, total, totalCash, totalAdjust);
	}
	
	@Override
	public PageBean selectVerifyDetailOrderListPage(PageBean pageBean) {
		
		return financeVerifyDal.selectVerifyDetailOrderListPage(pageBean);
	}

}

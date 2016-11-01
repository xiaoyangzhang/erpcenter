package org.yimayhd.erpcenter.facade.finance.service;

import java.util.List;
import java.util.Map;

import org.yimayhd.erpcenter.facade.finance.query.DeleteVerifyDetialDTO;
import org.yimayhd.erpcenter.facade.finance.query.QueryVerifyDetailDTO;
import org.yimayhd.erpcenter.facade.finance.query.QueryVerifyDetailTempDTO;
import org.yimayhd.erpcenter.facade.finance.query.SaveVerifyDetailDTO;
import org.yimayhd.erpcenter.facade.finance.query.SearchListDTO;
import org.yimayhd.erpcenter.facade.finance.query.UpdateVerifyDetailDTO;
import org.yimayhd.erpcenter.facade.finance.result.QueryVerifyDetailResult;
import org.yimayhd.erpcenter.facade.finance.result.ResultSupport;
import org.yimayhd.erpcenter.facade.finance.result.VerifyDetailResult;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;

/**
 * 财务管理
 * 
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface VerifyFacade{

	PageBean searchList(SearchListDTO dto);
	
	/**
	 * 修改对账单状态
	 */
	ResultSupport changeStatus(String id);
	
	/**
	 * 跳转到对账单详情页面
	 */
	VerifyDetailResult verifyDetail(Integer bizId, String verifyId);
	
	/**
	 * 跳转到对账单详情页面
	 */
	QueryVerifyDetailResult queryVerifyDetail(QueryVerifyDetailDTO dto);
	
	/**
	 * 跳转到对账单详情页面
	 */
	PageBean queyrVerifyDetailTemp(QueryVerifyDetailTempDTO dto);
	
	/**
	 * 跳转到对账单-订单详情页面
	 */
	PageBean queryVerifyDetailOrders(QueryVerifyDetailTempDTO dto);
	
	ResultSupport saveVerifyRecord(FinanceVerify verify, String bizCode);
	
	List<Map<String,String>> getSupplierNameList(Integer bizId, Integer supplierType, String supplierName);
	
	/**
	 * 删除对账单详情
	 */
	ResultSupport deleteVerifyDetail(DeleteVerifyDetialDTO dto);
	
	/**
	 * 删除对账单
	 */
	ResultSupport deleteVerify(Integer bizId, Integer id); 
	
	/**
	 * 批量添加对账单详情
	 */
	ResultSupport saveVerifyDetail(SaveVerifyDetailDTO dto);
	
	/**
	 * 批量更新对账单详情
	 */
	ResultSupport updateVerifyDetail(UpdateVerifyDetailDTO dto);
}

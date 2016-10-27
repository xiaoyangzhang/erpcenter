package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.budgetitem.SaveBudgetItemDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.budgetitem.BudgetItemResult;

/**
 * BudgetItemFacade
 * 
 * @author gaotingping
 * 2016年10月26日
 */
public interface BudgetItemFacade {

	//收入价格保存
	public BaseStateResult saveBudgetItem(SaveBudgetItemDTO saveBudgetItemDTO);

	//跳转到修改收入价格页面
	public BudgetItemResult editBudgetItem(Integer id);

	//修改收入价格
	public BaseStateResult updateBudgetItem(SaveBudgetItemDTO saveBudgetItemDTO);

	//删除
	public BaseStateResult deleteBudgetItemById(Integer id);

	//查询订单下是否有成本价格
	public BaseStateResult getTotalBudgetByOrderId(Integer orderId);

}

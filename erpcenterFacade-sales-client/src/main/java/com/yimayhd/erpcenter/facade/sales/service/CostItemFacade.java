package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.costitem.SaveCostItemDTO;
import com.yimayhd.erpcenter.facade.sales.query.costitem.ToAddProfitChangeDTO;
import com.yimayhd.erpcenter.facade.sales.query.costitem.ToSaveCostIncomeDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.costitem.CostItemResult;

/**
 * CostItemFacade
 * 
 * @author gaotingping
 * 2016年10月26日
 */
public interface CostItemFacade {

	//保存
	BaseStateResult toSaveCostIncome(ToSaveCostIncomeDTO toSaveCostIncomeDTO);

	//保存预算项目
	BaseStateResult saveCostItem(SaveCostItemDTO saveCostItemDTO);

	//修改预算项目回显数据
	CostItemResult editCostItem(Integer id);

	BaseStateResult updateCostItem(SaveCostItemDTO costItemDTO);

	BaseStateResult deleteCostItemById(Integer id);

	BaseStateResult toAddProfitChange(ToAddProfitChangeDTO profitChangeDTO);

}

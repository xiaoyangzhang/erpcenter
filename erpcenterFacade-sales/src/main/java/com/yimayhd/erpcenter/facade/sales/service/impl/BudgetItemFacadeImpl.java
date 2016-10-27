package com.yimayhd.erpcenter.facade.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.facade.sales.query.SaveBudgetItemDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.BudgetItemResult;
import com.yimayhd.erpcenter.facade.sales.service.BudgetItemFacade;

public class BudgetItemFacadeImpl implements BudgetItemFacade {
	
	@Autowired
	private GroupOrderPriceBiz groupOrderPriceService;

	public BaseStateResult saveBudgetItem(SaveBudgetItemDTO saveBudgetItemDTO) {

		groupOrderPriceService.insertSelective(saveBudgetItemDTO.getGroupOrderPrice());

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(true);

		return result;
	}

	@Override
	public BudgetItemResult editBudgetItem(Integer id) {

		GroupOrderPrice groupOrderPrice = groupOrderPriceService.selectByPrimaryKey(id);

		BudgetItemResult result = new BudgetItemResult();
		result.setGroupOrderPrice(groupOrderPrice);
		return result;
	}

	@Override
	public BaseStateResult updateBudgetItem(SaveBudgetItemDTO saveBudgetItemDTO) {

		GroupOrderPrice groupOrderPrice = saveBudgetItemDTO.getGroupOrderPrice();

		groupOrderPrice.setMode(0);
		groupOrderPriceService.updateByPrimaryKeySelective(groupOrderPrice);
		BaseStateResult result = new BaseStateResult();
		result.setSuccess(true);

		return result;
	}

	@Override
	public BaseStateResult deleteBudgetItemById(Integer id) {

		groupOrderPriceService.deleteByPrimaryKey(id);

		BaseStateResult result = new BaseStateResult();
		result.setSuccess(true);

		return result;
	}

	@Override
	public BaseStateResult getTotalBudgetByOrderId(Integer orderId) {

		BaseStateResult result = new BaseStateResult();

		Boolean flag = groupOrderPriceService.selectByOrderAndType(orderId);
		if (flag) {
			result.setSuccess(true);
		} else {
			result.setError("该团的成本价格没有维护！");
		}

		return result;
	}
}

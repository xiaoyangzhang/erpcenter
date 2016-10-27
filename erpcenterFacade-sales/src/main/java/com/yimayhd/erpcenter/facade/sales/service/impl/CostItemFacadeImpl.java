package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.facade.sales.query.costitem.SaveCostItemDTO;
import com.yimayhd.erpcenter.facade.sales.query.costitem.ToAddProfitChangeDTO;
import com.yimayhd.erpcenter.facade.sales.query.costitem.ToSaveCostIncomeDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.costitem.CostItemResult;
import com.yimayhd.erpcenter.facade.sales.service.CostItemFacade;

public class CostItemFacadeImpl implements CostItemFacade {

	@Autowired
	private GroupOrderPriceBiz groupOrderPriceService;

	public BaseStateResult toSaveCostIncome(ToSaveCostIncomeDTO toSaveCostIncomeDTO) {

		groupOrderPriceService.saveCostIncomeBatch(toSaveCostIncomeDTO.getCostIncome(),
				toSaveCostIncomeDTO.getEmployeeId(), toSaveCostIncomeDTO.getCurUserName());

		return new BaseStateResult(true, null);
	}

	public BaseStateResult saveCostItem(SaveCostItemDTO saveCostItemDTO) {

		groupOrderPriceService.insertSelective(saveCostItemDTO.getGroupOrderPrice());

		return new BaseStateResult(true, null);
	}

	public CostItemResult editCostItem(Integer id) {

		GroupOrderPrice groupOrderPrice = groupOrderPriceService.selectByPrimaryKey(id);

		CostItemResult result = new CostItemResult();
		result.setGroupOrderPrice(groupOrderPrice);

		return result;
	}

	public BaseStateResult updateCostItem(SaveCostItemDTO costItemDTO) {

		GroupOrderPrice groupOrderPrice = costItemDTO.getGroupOrderPrice();
		groupOrderPrice.setMode(1); // 0是预算，1是成本
		groupOrderPriceService.updateByPrimaryKeySelective(groupOrderPrice);

		return new BaseStateResult(true, null);
	}

	public BaseStateResult deleteCostItemById(Integer id) {
		groupOrderPriceService.deleteByPrimaryKey(id);
		return new BaseStateResult(true, null);
	}

	public BaseStateResult toAddProfitChange(ToAddProfitChangeDTO profitChangeDTO) {

		GroupOrderPrice gop = new GroupOrderPrice();
		gop.setOrderId(profitChangeDTO.getId());
		gop.setMode(1);
		gop.setRowState(1);
		gop.setPriceLockState(0);
		gop.setTotalPrice(profitChangeDTO.getPrice().doubleValue());
		gop.setItemName("其他");
		gop.setItemId(153);
		gop.setCreateTime(new Date().getTime());
		gop.setCreatorId(profitChangeDTO.getCreatorId());
		gop.setCreatorName(profitChangeDTO.getCreatorName());
		gop.setUnitPrice(profitChangeDTO.getPrice().doubleValue());
		gop.setNumTimes(new Double(1));
		gop.setNumPerson(new Double(1));

		groupOrderPriceService.insertSelective(gop);

		return new BaseStateResult(true, null);
	}
}

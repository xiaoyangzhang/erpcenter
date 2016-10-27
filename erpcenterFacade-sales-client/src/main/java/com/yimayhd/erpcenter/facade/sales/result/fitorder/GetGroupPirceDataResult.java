package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class GetGroupPirceDataResult extends BaseStateResult {

	private List<ProductGroupVo> priceGroup;

	public List<ProductGroupVo> getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(List<ProductGroupVo> priceGroup) {
		this.priceGroup = priceGroup;
	}
}

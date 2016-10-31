package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.math.BigDecimal;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalePrice;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToDeliveryPriceTableResult extends BaseStateResult {
	private static final long serialVersionUID = -6273238328394737918L;
	private PageBean<SalePrice> pageBean;
	private BigDecimal totals;

	public PageBean<SalePrice> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<SalePrice> pageBean) {
		this.pageBean = pageBean;
	}

	public BigDecimal getTotals() {
		return totals;
	}

	public void setTotals(BigDecimal totals) {
		this.totals = totals;
	}
}

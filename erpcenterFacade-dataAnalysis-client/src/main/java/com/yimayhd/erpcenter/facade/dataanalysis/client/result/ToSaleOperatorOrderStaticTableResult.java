package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorOrderStatic;

public class ToSaleOperatorOrderStaticTableResult extends BaseResult {
	private static final long serialVersionUID = -6479326066375342799L;
	private SaleOperatorOrderStatic sum;
	private PageBean<SaleOperatorOrderStatic> pageBean;

	public SaleOperatorOrderStatic getSum() {
		return sum;
	}

	public void setSum(SaleOperatorOrderStatic sum) {
		this.sum = sum;
	}

	public PageBean<SaleOperatorOrderStatic> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<SaleOperatorOrderStatic> pageBean) {
		this.pageBean = pageBean;
	}
}

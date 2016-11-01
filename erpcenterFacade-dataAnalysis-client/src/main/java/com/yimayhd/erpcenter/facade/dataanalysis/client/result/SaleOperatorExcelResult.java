package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;

public class SaleOperatorExcelResult extends BaseResult {
	private static final long serialVersionUID = -5749319810947333094L;
	private PageBean<SaleOperatorVo> pageBean;

	public PageBean<SaleOperatorVo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<SaleOperatorVo> pageBean) {
		this.pageBean = pageBean;
	}
}

package com.yimayhd.erpcenter.facade.sales.result.quality;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class LoadQualityListResult extends BaseStateResult {
	private static final long serialVersionUID = 705332899115598175L;
	private PageBean pageBean;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}

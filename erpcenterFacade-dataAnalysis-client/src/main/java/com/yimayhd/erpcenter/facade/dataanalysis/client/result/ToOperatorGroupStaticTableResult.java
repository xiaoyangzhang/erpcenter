package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;

public class ToOperatorGroupStaticTableResult extends BaseResult {
	private static final long serialVersionUID = -6479326066375342799L;
	private OperatorGroupStatic sum;
	private PageBean<OperatorGroupStatic> pageBean;

	public OperatorGroupStatic getSum() {
		return sum;
	}

	public void setSum(OperatorGroupStatic sum) {
		this.sum = sum;
	}

	public PageBean<OperatorGroupStatic> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<OperatorGroupStatic> pageBean) {
		this.pageBean = pageBean;
	}
}

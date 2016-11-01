package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;

public class ToSaleOperatorTableResult extends BaseResult {
	private static final long serialVersionUID = -5241990099703094968L;
	private PageBean<SaleOperatorVo> pageBean;
	private Map<String, Object> sumPerson;

	public PageBean<SaleOperatorVo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<SaleOperatorVo> pageBean) {
		this.pageBean = pageBean;
	}

	public Map<String, Object> getSumPerson() {
		return sumPerson;
	}

	public void setSumPerson(Map<String, Object> sumPerson) {
		this.sumPerson = sumPerson;
	}
}

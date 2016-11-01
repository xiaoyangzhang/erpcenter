package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;

public class ToSaleOperatorPreviewResult extends BaseResult {
	private static final long serialVersionUID = -5241990099703094968L;
	private PageBean<SaleOperatorVo> pageBean;
	private SaleOperatorVo order;
	private List<DicInfo> jdxjList;

	public PageBean<SaleOperatorVo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<SaleOperatorVo> pageBean) {
		this.pageBean = pageBean;
	}

	public SaleOperatorVo getOrder() {
		return order;
	}

	public void setOrder(SaleOperatorVo order) {
		this.order = order;
	}

	public List<DicInfo> getJdxjList() {
		return jdxjList;
	}

	public void setJdxjList(List<DicInfo> jdxjList) {
		this.jdxjList = jdxjList;
	}
}

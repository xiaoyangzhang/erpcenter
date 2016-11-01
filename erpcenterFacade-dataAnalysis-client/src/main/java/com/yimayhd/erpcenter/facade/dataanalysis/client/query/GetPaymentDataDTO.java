package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;

public class GetPaymentDataDTO extends BaseDTO {
	private static final long serialVersionUID = -8025185387986462362L;
	private Integer page;
	private Integer pageSize;
	private PaymentExportVO vo;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PaymentExportVO getVo() {
		return vo;
	}

	public void setVo(PaymentExportVO vo) {
		this.vo = vo;
	}
}

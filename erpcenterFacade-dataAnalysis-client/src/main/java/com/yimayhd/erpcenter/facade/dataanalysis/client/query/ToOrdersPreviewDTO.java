package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;

public class ToOrdersPreviewDTO extends BaseDTO {
	private static final long serialVersionUID = -4574243124788386105L;
	private PaymentExportVO vo;
	private Map parameters;

	public PaymentExportVO getVo() {
		return vo;
	}

	public void setVo(PaymentExportVO vo) {
		this.vo = vo;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}

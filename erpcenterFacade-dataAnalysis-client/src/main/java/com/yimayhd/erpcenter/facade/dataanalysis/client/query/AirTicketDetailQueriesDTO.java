package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

public class AirTicketDetailQueriesDTO extends BaseDTO {
	private static final long serialVersionUID = 8996613045415659802L;
	private Integer bizId;
	private Map parameters;

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}

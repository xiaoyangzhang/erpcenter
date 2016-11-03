package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

public class GetAgeListByProductDTO extends BaseDTO {
	private static final long serialVersionUID = 6353267858048728734L;
	private Map queryParamters;

	public Map getQueryParamters() {
		return queryParamters;
	}

	public void setQueryParamters(Map queryParamters) {
		this.queryParamters = queryParamters;
	}
}

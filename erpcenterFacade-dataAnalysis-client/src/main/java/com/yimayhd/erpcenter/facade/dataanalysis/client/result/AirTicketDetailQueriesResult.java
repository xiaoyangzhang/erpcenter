package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

public class AirTicketDetailQueriesResult extends BaseResult {
	private static final long serialVersionUID = -2213840015175054783L;
	private List<DicInfo> cashTypes;
	private Map parameters;

	public List<DicInfo> getCashTypes() {
		return cashTypes;
	}

	public void setCashTypes(List<DicInfo> cashTypes) {
		this.cashTypes = cashTypes;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}

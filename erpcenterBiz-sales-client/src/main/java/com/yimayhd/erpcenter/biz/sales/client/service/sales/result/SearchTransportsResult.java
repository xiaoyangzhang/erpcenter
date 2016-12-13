package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;

public class SearchTransportsResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TransPort> transPorts;

	public List<TransPort> getTransPorts() {
		return transPorts;
	}

	public void setTransPorts(List<TransPort> transPorts) {
		this.transPorts = transPorts;
	}
	
	
}

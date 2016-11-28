package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class ShopSalesStatisticsQueryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2161253902104381249L;
	
	private PlatTaobaoTrade platTaobaoTrade;
	
	private Integer bizId;

	public PlatTaobaoTrade getPlatTaobaoTrade() {
		return platTaobaoTrade;
	}

	public void setPlatTaobaoTrade(PlatTaobaoTrade platTaobaoTrade) {
		this.platTaobaoTrade = platTaobaoTrade;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	

}

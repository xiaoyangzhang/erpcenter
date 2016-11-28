package com.yimayhd.erpcenter.facade.tj.client.result;

import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class ShopSalesStatisticsResult  extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -674816147243138520L;
	
	private PlatTaobaoTrade trade;

	public PlatTaobaoTrade getTrade() {
		return trade;
	}

	public void setTrade(PlatTaobaoTrade trade) {
		this.trade = trade;
	}
	
	

}

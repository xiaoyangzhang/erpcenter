package com.yimayhd.erpcenter.facade.tj.client.result;

import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;

public class TJGroupProfitResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TJGroupProfit tjGroupProfit;

	public TJGroupProfit getTjGroupProfit() {
		return tjGroupProfit;
	}

	public void setTjGroupProfit(TJGroupProfit tjGroupProfit) {
		this.tjGroupProfit = tjGroupProfit;
	}
	
	

}

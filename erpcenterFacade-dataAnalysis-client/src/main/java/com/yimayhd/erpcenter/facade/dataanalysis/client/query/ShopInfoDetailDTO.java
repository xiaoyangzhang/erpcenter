package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.operation.vo.QueryShopInfo;

public class ShopInfoDetailDTO extends BaseDTO {
	private static final long serialVersionUID = 3281919789736533335L;
	private QueryShopInfo shop;
	private Map queryParamters;

	public QueryShopInfo getShop() {
		return shop;
	}

	public void setShop(QueryShopInfo shop) {
		this.shop = shop;
	}

	public Map getQueryParamters() {
		return queryParamters;
	}

	public void setQueryParamters(Map queryParamters) {
		this.queryParamters = queryParamters;
	}
}

package com.yimayhd.erpcenter.facade.operation.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.operation.vo.QueryGuideShop;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;


public class BookingShopListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bizId;
	private String orgIds;
	private String saleOperatorIds;
	private TourGroup group;
	private QueryGuideShop queryGuideShop;
	private Set<Integer> dataUserIds;
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public TourGroup getGroup() {
		return group;
	}
	public void setGroup(TourGroup group) {
		this.group = group;
	}
	
	public Set<Integer> getDataUserIds() {
		return dataUserIds;
	}
	public void setDataUserIds(Set<Integer> dataUserIds) {
		this.dataUserIds = dataUserIds;
	}
	public QueryGuideShop getQueryGuideShop() {
		return queryGuideShop;
	}
	public void setQueryGuideShop(QueryGuideShop queryGuideShop) {
		this.queryGuideShop = queryGuideShop;
	}
	
	
}

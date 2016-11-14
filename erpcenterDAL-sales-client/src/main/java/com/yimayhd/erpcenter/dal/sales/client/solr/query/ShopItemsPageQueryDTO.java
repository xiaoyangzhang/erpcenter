package com.yimayhd.erpcenter.dal.sales.client.solr.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class ShopItemsPageQueryDTO extends PageQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bsdGoodsName;//购物项目名称  判空
	private String bsdGoodsNameFromShop;//购物项目名称来自己于购物店 必传
	private long bsSupplierId;//
	private String bsSupplierName;//判空 LIKE
	
	private String dateType;//日期类型 必传 groupDate或appliDate
	private Long startTime;//开始时间
	private Long endTime;//结束时间
	
	private Integer saleType;// ==0时查询goSaleOperatorId，==1时查询tgOperatorId
	private long tgOperatorId;//计调id Str，带权限时必传 IN
	private long goSaleOperatorId;//销售计调id Str，带权限时必传 IN
	
	private long tgBizId;//bizId 必传
	private Set<Integer> listTypeIds;;//权限-计调id，带权限时必传 IN
	
	private Long tgPrudctBrandId;//产品品牌ID
	private String tgProductName;//产品名称
//	<IF test="page.parameter.groupMode == 0">
//		AND g.group_mode <![CDATA[<]]>1
//	</IF>
//	<IF test="page.parameter.groupMode == 1">
//		AND g.group_mode <![CDATA[>]]>0
//	</IF>
	private int tgGroupMode;
	private String tgGroupState;//团状态Str（以","分隔） IN
	public String getBsdGoodsName() {
		return bsdGoodsName;
	}
	public void setBsdGoodsName(String bsdGoodsName) {
		this.bsdGoodsName = bsdGoodsName;
	}
	public String getBsSupplierName() {
		return bsSupplierName;
	}
	public void setBsSupplierName(String bsSupplierName) {
		this.bsSupplierName = bsSupplierName;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Integer getSaleType() {
		return saleType;
	}
	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}
	public long getTgOperatorId() {
		return tgOperatorId;
	}
	public void setTgOperatorId(long tgOperatorId) {
		this.tgOperatorId = tgOperatorId;
	}
	public long getGoSaleOperatorId() {
		return goSaleOperatorId;
	}
	public void setGoSaleOperatorId(long goSaleOperatorId) {
		this.goSaleOperatorId = goSaleOperatorId;
	}
	public long getTgBizId() {
		return tgBizId;
	}
	public void setTgBizId(long tgBizId) {
		this.tgBizId = tgBizId;
	}

	public Set<Integer> getListTypeIds() {
		return listTypeIds;
	}
	public void setListTypeIds(Set<Integer> listTypeIds) {
		this.listTypeIds = listTypeIds;
	}
	
	public Long getTgPrudctBrandId() {
		return tgPrudctBrandId;
	}
	public void setTgPrudctBrandId(Long tgPrudctBrandId) {
		this.tgPrudctBrandId = tgPrudctBrandId;
	}
	public String getTgProductName() {
		return tgProductName;
	}
	public void setTgProductName(String tgProductName) {
		this.tgProductName = tgProductName;
	}
	public int getTgGroupMode() {
		return tgGroupMode;
	}
	public void setTgGroupMode(int tgGroupMode) {
		this.tgGroupMode = tgGroupMode;
	}
	public String getTgGroupState() {
		return tgGroupState;
	}
	public void setTgGroupState(String tgGroupState) {
		this.tgGroupState = tgGroupState;
	}
	public long getBsSupplierId() {
		return bsSupplierId;
	}
	public void setBsSupplierId(long bsSupplierId) {
		this.bsSupplierId = bsSupplierId;
	}
	public String getBsdGoodsNameFromShop() {
		return bsdGoodsNameFromShop;
	}
	public void setBsdGoodsNameFromShop(String bsdGoodsNameFromShop) {
		this.bsdGoodsNameFromShop = bsdGoodsNameFromShop;
	}
	
}

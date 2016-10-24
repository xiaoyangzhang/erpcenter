package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.yihg.sales.constants.Constants;

public class GroupRouteSupplier implements Serializable {
	private Integer id;

	private Integer routeId;

	private Integer supplierType;
	private String supplierTypeName;

	private Integer subType;

	private Integer supplierId;

	private String supplierName;

	private Long createTime;
	private static final Map<Integer, String> supplierTypeMap = new HashMap<Integer, String>();
	static {
		supplierTypeMap.put(Constants.TRAVELAGENCY, "旅行社");
		supplierTypeMap.put(Constants.RESTAURANT, "餐厅");
		supplierTypeMap.put(Constants.HOTEL, "酒店");
		supplierTypeMap.put(Constants.FLEET, "车队");
		supplierTypeMap.put(Constants.SCENICSPOT, "旅行社");
		supplierTypeMap.put(Constants.TRAVELAGENCY, "景区");
		supplierTypeMap.put(Constants.SHOPPING, "购物");
		supplierTypeMap.put(Constants.ENTERTAINMENT, "娱乐");
		supplierTypeMap.put(Constants.GUIDE, "导游");
		supplierTypeMap.put(Constants.AIRTICKETAGENT, "机票代理");
		supplierTypeMap.put(Constants.TRAINTICKETAGENT, "火车票代理");
		supplierTypeMap.put(Constants.GOLF, "高尔夫");
		supplierTypeMap.put(Constants.OTHER, "其他");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierTypeName = supplierTypeMap.get(supplierType);
		this.supplierType = supplierType;
	}

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	public String getSupplierTypeName() {
		return supplierTypeName;
	}

	public void setSupplierTypeName(String supplierTypeName) {
		this.supplierTypeName = supplierTypeName;
	}

}
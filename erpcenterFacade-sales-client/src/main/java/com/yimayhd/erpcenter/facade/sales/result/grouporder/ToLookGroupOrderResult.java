package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class ToLookGroupOrderResult extends BaseStateResult {
	private static final long serialVersionUID = -114826605198026808L;
	private GroupOrder groupOrder;
	private PlatformEmployeePo saleEmployeePo;
	private PlatformEmployeePo operaEmployeePo;
	private ProductInfo productInfo;
	private SupplierInfo supplierInfo;
	private List<DicInfo> jdxjList;
	private List<GroupOrderPrice> costList;
	private Double income;
	private List<GroupOrderPrice> budgetList;
	private Double budget;
	private List<GroupOrderTransport> transportList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> zjlxList;
	private List<DicInfo> lysfxmList;
	private List<GroupRequirement> restaurantList;
	private List<GroupRequirement> airticketagentList;
	private List<GroupRequirement> trainticketagentList;
	private List<GroupOrderGuest> guestList;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public PlatformEmployeePo getSaleEmployeePo() {
		return saleEmployeePo;
	}

	public void setSaleEmployeePo(PlatformEmployeePo saleEmployeePo) {
		this.saleEmployeePo = saleEmployeePo;
	}

	public PlatformEmployeePo getOperaEmployeePo() {
		return operaEmployeePo;
	}

	public void setOperaEmployeePo(PlatformEmployeePo operaEmployeePo) {
		this.operaEmployeePo = operaEmployeePo;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public SupplierInfo getSupplierInfo() {
		return supplierInfo;
	}

	public void setSupplierInfo(SupplierInfo supplierInfo) {
		this.supplierInfo = supplierInfo;
	}

	public List<DicInfo> getJdxjList() {
		return jdxjList;
	}

	public void setJdxjList(List<DicInfo> jdxjList) {
		this.jdxjList = jdxjList;
	}

	public List<GroupOrderPrice> getCostList() {
		return costList;
	}

	public void setCostList(List<GroupOrderPrice> costList) {
		this.costList = costList;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public List<GroupOrderPrice> getBudgetList() {
		return budgetList;
	}

	public void setBudgetList(List<GroupOrderPrice> budgetList) {
		this.budgetList = budgetList;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public List<GroupOrderTransport> getTransportList() {
		return transportList;
	}

	public void setTransportList(List<GroupOrderTransport> transportList) {
		this.transportList = transportList;
	}

	public List<DicInfo> getJtfsList() {
		return jtfsList;
	}

	public void setJtfsList(List<DicInfo> jtfsList) {
		this.jtfsList = jtfsList;
	}

	public List<DicInfo> getZjlxList() {
		return zjlxList;
	}

	public void setZjlxList(List<DicInfo> zjlxList) {
		this.zjlxList = zjlxList;
	}

	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}

	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}

	public List<GroupRequirement> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<GroupRequirement> restaurantList) {
		this.restaurantList = restaurantList;
	}

	public List<GroupRequirement> getAirticketagentList() {
		return airticketagentList;
	}

	public void setAirticketagentList(List<GroupRequirement> airticketagentList) {
		this.airticketagentList = airticketagentList;
	}

	public List<GroupRequirement> getTrainticketagentList() {
		return trainticketagentList;
	}

	public void setTrainticketagentList(List<GroupRequirement> trainticketagentList) {
		this.trainticketagentList = trainticketagentList;
	}

	public List<GroupOrderGuest> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<GroupOrderGuest> guestList) {
		this.guestList = guestList;
	}
}

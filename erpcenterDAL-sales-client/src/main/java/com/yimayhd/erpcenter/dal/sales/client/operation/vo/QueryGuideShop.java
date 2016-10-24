package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class QueryGuideShop implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer groupId ;
	private String groupCode;
	private Integer groupMode ;
	private String supplierName;
	private String receiveMode;
	private Integer adultNum;
	private Integer childNum;
	private String productBrandName;
	private String productName;
	private String source;
	private String sourceType;
	private String userName;
	private BigDecimal totalFee;
	private String shopName;
	private String shopFee;
	private String guideName;
	private String[] shopNameArr;
	private String[] shopFeeArr;
	private String[] guideNameArr;
	private String saleOperatorIds;
	private Integer pageSize=15;
	private Integer page=1;
	//查询条件
	private Integer dateType;
	private Date startTime ;
	private Date endTime;
	private Integer bizId;
	private Integer sourceTypeId;
	private Integer provinceId;
	private Integer cityId;
	private String operatorIds;
	private String orgIds;
	/**
	 * 购物数据录入状态
	 */
	private Integer shoppingDataState;
	
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getAdultNum() {
		return adultNum;
	}
	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}
	public Integer getChildNum() {
		return childNum;
	}
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public String getShopFee() {
		return shopFee;
	}
	public void setShopFee(String shopFee) {
		this.shopFee = shopFee;
	}
	public String[] getShopFeeArr() {
		if(!isEmpty(this.shopFee)){
			return shopFee.split(",");
		}
		return shopFeeArr;
	}
	public String[] getShopNameArr() {
		if(!isEmpty(this.shopName)){
			return shopName.split(",");
		}
		return shopNameArr;
	}
	public String[] getGuideNameArr() {
		if(!isEmpty(this.guideName)){
			return guideName.split(",");
		}
		return guideNameArr;
	}
	public Integer getSourceTypeId() {
		return sourceTypeId;
	}
	public void setSourceTypeId(Integer sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public boolean isEmpty(String arg){
		return arg==null || arg.length()==0;
	}
	public Integer getShoppingDataState() {
		return shoppingDataState;
	}
	public void setShoppingDataState(Integer shoppingDataState) {
		this.shoppingDataState = shoppingDataState;
	}
	
}

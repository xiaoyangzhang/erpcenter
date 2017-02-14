package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class QueryCommissionGrantDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private String startTime;
	private String endTime;
	private String groupCode;
	private Integer productBrandId;
	private String productName;
	private String orgIds;
	private String guideName;
	private String saleOperatorIds;
	private Map paramters;
	private String carInfo;
	private Integer status; 
	private String commProjectTypeCodes;
	private String lrStatus;
	private Set<Integer> set;
	
	
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getProductBrandId() {
		return productBrandId;
	}
	public void setProductBrandId(Integer productBrandId) {
		this.productBrandId = productBrandId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public Map getParamters() {
		return paramters;
	}
	public void setParamters(Map paramters) {
		this.paramters = paramters;
	}
	public String getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCommProjectTypeCodes() {
		return commProjectTypeCodes;
	}
	public void setCommProjectTypeCodes(String commProjectTypeCodes) {
		this.commProjectTypeCodes = commProjectTypeCodes;
	}
	public String getLrStatus() {
		return lrStatus;
	}
	public void setLrStatus(String lrStatus) {
		this.lrStatus = lrStatus;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
}

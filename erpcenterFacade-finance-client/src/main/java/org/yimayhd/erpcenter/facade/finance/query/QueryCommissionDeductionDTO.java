package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

public class QueryCommissionDeductionDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private String startTime;
	private String endTime;
	private String groupCode;
	private String carInfo;
	private Integer productBrandId;
	private String productName;
	private String orgIds;
	private Integer status;
	private String commProjectTypeCodes;
	private String lrStatus;
	private String saleOperatorIds;
	private String guideName;
	private Map paramters;
	private Set<Integer> set;
	
	
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Map getParamters() {
		return paramters;
	}
	public void setParamters(Map paramters) {
		this.paramters = paramters;
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
	public String getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
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
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	
	
    
	
}

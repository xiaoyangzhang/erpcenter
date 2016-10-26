package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ProductGuestShoppingCondition implements Serializable {
	private Integer page;
	private Integer pageSize;
	private Date dateStart;
	private Date dateEnd;
	private Integer provinceId;
	private Integer cityId;
	private Set<Integer> dataRightSet;
	private String operatorIds;
	private String orgIds;
	private Integer bizId;
	private String productBrandName;
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Set<Integer> getDataRightSet() {
		return dataRightSet;
	}
	public void setDataRightSet(Set<Integer> dataRightSet) {
		this.dataRightSet = dataRightSet;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	
}

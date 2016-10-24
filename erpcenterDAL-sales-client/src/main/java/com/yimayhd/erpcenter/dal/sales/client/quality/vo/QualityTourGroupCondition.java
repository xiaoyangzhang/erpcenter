package com.yimayhd.erpcenter.dal.sales.client.quality.vo;

import java.io.Serializable;
import java.util.Date;

import com.yihg.supplier.constants.Constants;

/**
 * 质量列表查询条件
 * @author Administrator
 *
 */
public class QualityTourGroupCondition implements Serializable {
	private Integer bizId;
	private Date startTime;
	private Date endTime;
	private String groupCode;
	private Integer operatorIds;
	private Integer productBrandId;
	private String productName;
	private Integer groupMode;
	private Integer page;
	private Integer pageSize;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
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
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public Integer getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(Integer operatorIds) {
		this.operatorIds = operatorIds;
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
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public Integer getPage() {
		if(page == null){
			page = 1;
		}
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		if(pageSize == null){
			pageSize = Constants.PAGESIZE;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}

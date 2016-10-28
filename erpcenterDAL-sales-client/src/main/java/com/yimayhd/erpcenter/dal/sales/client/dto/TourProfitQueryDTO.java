package com.yimayhd.erpcenter.dal.sales.client.dto;

import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class TourProfitQueryDTO extends PageQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = 651118350169409846L;
	
	
	private Integer bizId;
	
	private List<Integer> operatorIds; 
	
	private String groupCodeLike ;
	
	private String productNameLike ;
	
	private List<Integer> saleOperatorIds;
	
	private Date startTime;
	
	private Date endTime;

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public List<Integer> getOperatorIds() {
		return operatorIds;
	}

	public void setOperatorIds(List<Integer> operatorIds) {
		this.operatorIds = operatorIds;
	}

	public String getGroupCodeLike() {
		return groupCodeLike;
	}

	public void setGroupCodeLike(String groupCodeLike) {
		this.groupCodeLike = groupCodeLike;
	}

	public String getProductNameLike() {
		return productNameLike;
	}

	public void setProductNameLike(String productNameLike) {
		this.productNameLike = productNameLike;
	}

	public List<Integer> getSaleOperatorIds() {
		return saleOperatorIds;
	}

	public void setSaleOperatorIds(List<Integer> saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
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
	
	
}

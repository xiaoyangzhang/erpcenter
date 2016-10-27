package com.yimayhd.erpcenter.dal.sales.client.query.vo;

import java.io.Serializable;
import java.util.Date;

public class DepartmentOrderResult implements Serializable {
	private Integer numAdult;
	private Integer numChild;
	private Long createTime;
	private Date createDate;
	private Date departureDate;
	private Integer operatorId;
	private String operatorName;
	private Integer affirmOrderCount;
	private Integer reserveOrderCount;
	public Integer getNumAdult() {
		return numAdult;
	}
	public void setNumAdult(Integer numAdult) {
		this.numAdult = numAdult;
	}
	public Integer getNumChild() {
		return numChild;
	}
	public void setNumChild(Integer numChild) {
		this.numChild = numChild;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getAffirmOrderCount() {
		return affirmOrderCount;
	}
	public void setAffirmOrderCount(Integer affirmOrderCount) {
		this.affirmOrderCount = affirmOrderCount;
	}
	public Integer getReserveOrderCount() {
		return reserveOrderCount;
	}
	public void setReserveOrderCount(Integer reserveOrderCount) {
		this.reserveOrderCount = reserveOrderCount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

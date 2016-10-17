package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.Date;

public class PriceCopyVo implements Serializable {
	private Integer groupId;
	private Date startTime;
	private Date endTime;
	private Integer destYear;
	private Integer destMonth;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
	public Integer getDestYear() {
		return destYear;
	}
	public void setDestYear(Integer destYear) {
		this.destYear = destYear;
	}
	public Integer getDestMonth() {
		return destMonth;
	}
	public void setDestMonth(Integer destMonth) {
		this.destMonth = destMonth;
	}
	
	
}

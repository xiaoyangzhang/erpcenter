package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingGuideListCount implements Serializable{

	
	/**
	 * 带团查询
	 */
	private static final long serialVersionUID = 5989227744888392196L;
	private Integer guideId;
	private String guideName;
	private String guideMobile;
	private String guideNo;
	private Integer adult;
	private BigDecimal jh;//计划购物金额
	private BigDecimal sj;//实际购物金额
	private Integer state0;//未报账
	private Integer state1;//处理中
	private Integer state2;//已报账
	private Date startTime;
	private Date endTime;
	private Integer groupCount;
	
	
	
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
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getGuideMobile() {
		return guideMobile;
	}
	public void setGuideMobile(String guideMobile) {
		this.guideMobile = guideMobile;
	}
	public String getGuideNo() {
		return guideNo;
	}
	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}
	public Integer getAdult() {
		return adult;
	}
	public void setAdult(Integer adult) {
		this.adult = adult;
	}
	public BigDecimal getJh() {
		return jh;
	}
	public void setJh(BigDecimal jh) {
		this.jh = jh;
	}
	public BigDecimal getSj() {
		return sj;
	}
	public void setSj(BigDecimal sj) {
		this.sj = sj;
	}

	public Integer getState0() {
		return state0;
	}
	public void setState0(Integer state0) {
		this.state0 = state0;
	}
	public Integer getState1() {
		return state1;
	}
	public void setState1(Integer state1) {
		this.state1 = state1;
	}
	public Integer getState2() {
		return state2;
	}
	public void setState2(Integer state2) {
		this.state2 = state2;
	}
	public Integer getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}
	
	
	
}

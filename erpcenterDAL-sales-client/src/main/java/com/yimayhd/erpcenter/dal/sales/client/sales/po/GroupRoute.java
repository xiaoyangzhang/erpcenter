package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.erpcenter.common.util.LogFieldAnno;


public class GroupRoute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@LogFieldAnno(isKey = true)
	private Integer id;

	private Integer groupId;

	private Integer orderId;

	private Integer dayNum;
	@LogFieldAnno(description="日期", delOrIns = true)
	private Date groupDate;
	@LogFieldAnno(description="早餐", delOrIns = true)
	private String breakfast;
	@LogFieldAnno(description="中餐", delOrIns = true)
	private String lunch;
	@LogFieldAnno(description="晚餐", delOrIns = true)
	private String supper;

	private Integer hotelId;
	@LogFieldAnno(description="酒店", delOrIns = true)
	private String hotelName;
	@LogFieldAnno(description="行程", delOrIns = true)
	private String routeDesp;
	@LogFieldAnno(description="提示")
	private String routeTip;

	private Long createTime;
	
	private String maxDay;
	
	private Integer numDay;
	
	

	public String getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(String maxDay) {
		this.maxDay = maxDay;
	}

	public Integer getNumDay() {
		return numDay;
	}

	public void setNumDay(Integer numDay) {
		this.numDay = numDay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDayNum() {
		return dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public Date getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast == null ? null : breakfast.trim();
	}


	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch == null ? null : lunch.trim();
	}

	public String getSupper() {
		return supper;
	}

	public void setSupper(String supper) {
		this.supper = supper == null ? null : supper.trim();
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRouteDesp() {
		return routeDesp;
	}

	public void setRouteDesp(String routeDesp) {
		this.routeDesp = routeDesp == null ? null : routeDesp.trim();
	}

	public String getRouteTip() {
		return routeTip;
	}

	public void setRouteTip(String routeTip) {
		this.routeTip = routeTip == null ? null : routeTip.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}
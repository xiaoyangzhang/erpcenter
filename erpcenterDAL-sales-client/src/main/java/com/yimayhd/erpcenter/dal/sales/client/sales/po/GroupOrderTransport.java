package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.yimayhd.erpcenter.common.util.LogFieldAnno;

public class GroupOrderTransport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@LogFieldAnno(isKey = true)
	private Integer id;

	private Integer orderId;

	private Integer method;

	private Integer type;
	 @LogFieldAnno(description="班次", delOrIns = true)
	private String classNo;
	 @LogFieldAnno(description="出发地", delOrIns = true)
	private String departureCity;

	private String departureStation;
	 @LogFieldAnno(description="日期", delOrIns = true)
	private Date departureDate;
	 @LogFieldAnno(description="时间", delOrIns = true)
	private String departureTime;

	private String arrivalCity;
	 @LogFieldAnno(description="到达地", delOrIns = true)
	private String arrivalStation;
	private Date arrivalDate;
	 @LogFieldAnno(description="时间", delOrIns = true)
	private String arrivalTime;

	private Integer isDirect;

	private String destination;

	private Long createTime;
	
	private Integer sourceType ; //0省内，1省外

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo == null ? null : classNo.trim();
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity == null ? null : departureCity
				.trim();
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation == null ? null
				: departureStation.trim();
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime == null ? null : departureTime
				.trim();
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity == null ? null : arrivalCity.trim();
	}

	public String getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(String arrivalStation) {
		this.arrivalStation = arrivalStation == null ? null : arrivalStation
				.trim();
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime == null ? null : arrivalTime.trim();
	}

	public Integer getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(Integer isDirect) {
		this.isDirect = isDirect;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination == null ? null : destination.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@JSONField (format="yyyy-MM-dd")  
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
}
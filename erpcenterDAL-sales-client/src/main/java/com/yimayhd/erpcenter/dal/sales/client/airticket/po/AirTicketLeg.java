package com.yimayhd.erpcenter.dal.sales.client.airticket.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AirTicketLeg implements Serializable{

	/**
	 * leg version
	 */
	private static final long serialVersionUID = 236675295841879669L;

	private Integer id; 
	
	private Integer resourceId;
	
	private Date depDate;
	
	private String depCity;
	
	private String arrCity;
	
	private String airCode;
	
	private String seatType;
	
	private Date depTime;
	
	private Date arrTime;
	/**
	 * 主键
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 资源ID
	 * @return
	 */
	public Integer getResourceId() {
		return resourceId;
	}
	/**
	 * 资源ID
	 * @param resourceId
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * 出发日期
	 * @return
	 */
	public Date getDepDate() {
		return depDate;
	}
	/**
	 * 出发日期
	 * @param depDate
	 */
	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}
	/**
	 * 出发城市
	 * @return
	 */
	public String getDepCity() {
		return depCity;
	}
	/**
	 * 出发城市
	 * @param depCity
	 */
	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}
	/**
	 * 目的地
	 * @return
	 */
	public String getArrCity() {
		return arrCity;
	}
	/**
	 * 目的地
	 * @param arrCity
	 */
	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}
	/**
	 * 航班号
	 * @return
	 */
	public String getAirCode() {
		return airCode;
	}
	/**
	 * 航班号
	 * @param airCode
	 */
	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}
	/**
	 * 座位类型
	 * @return
	 */
	public String getSeatType() {
		return seatType;
	}
	/**
	 * 座位类型
	 * @param seatType
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public Date getDepTime() {
		return depTime;
	}
	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}
	public Date getArrTime() {
		return arrTime;
	}
	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}
	
}

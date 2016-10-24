package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.Date;

public class AssistantGroupRoute  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 431504586176335190L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.route_id
	 * @mbggenerated
	 */
	private Integer routeId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.group_id
	 * @mbggenerated
	 */
	private Integer groupId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.order_id
	 * @mbggenerated
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.day_num
	 * @mbggenerated
	 */
	private Integer dayNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.group_date
	 * @mbggenerated
	 */
	private String groupDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.breakfast
	 * @mbggenerated
	 */
	private String breakfast;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.lunch
	 * @mbggenerated
	 */
	private String lunch;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.supper
	 * @mbggenerated
	 */
	private String supper;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.hotel_name
	 * @mbggenerated
	 */
	private String hotelName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.route_tip
	 * @mbggenerated
	 */
	private String routeTip;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_route.route_desp
	 * @mbggenerated
	 */
	private String routeDesp;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.id
	 * @return  the value of assistant_group_route.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.id
	 * @param id  the value for assistant_group_route.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.route_id
	 * @return  the value of assistant_group_route.route_id
	 * @mbggenerated
	 */
	public Integer getRouteId() {
		return routeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.route_id
	 * @param routeId  the value for assistant_group_route.route_id
	 * @mbggenerated
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.group_id
	 * @return  the value of assistant_group_route.group_id
	 * @mbggenerated
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.group_id
	 * @param groupId  the value for assistant_group_route.group_id
	 * @mbggenerated
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.order_id
	 * @return  the value of assistant_group_route.order_id
	 * @mbggenerated
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.order_id
	 * @param orderId  the value for assistant_group_route.order_id
	 * @mbggenerated
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.day_num
	 * @return  the value of assistant_group_route.day_num
	 * @mbggenerated
	 */
	public Integer getDayNum() {
		return dayNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.day_num
	 * @param dayNum  the value for assistant_group_route.day_num
	 * @mbggenerated
	 */
	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.group_date
	 * @return  the value of assistant_group_route.group_date
	 * @mbggenerated
	 */
	public String getGroupDate() {
		return groupDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.group_date
	 * @param groupDate  the value for assistant_group_route.group_date
	 * @mbggenerated
	 */
	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.breakfast
	 * @return  the value of assistant_group_route.breakfast
	 * @mbggenerated
	 */
	public String getBreakfast() {
		return breakfast;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.breakfast
	 * @param breakfast  the value for assistant_group_route.breakfast
	 * @mbggenerated
	 */
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast == null ? null : breakfast.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.lunch
	 * @return  the value of assistant_group_route.lunch
	 * @mbggenerated
	 */
	public String getLunch() {
		return lunch;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.lunch
	 * @param lunch  the value for assistant_group_route.lunch
	 * @mbggenerated
	 */
	public void setLunch(String lunch) {
		this.lunch = lunch == null ? null : lunch.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.supper
	 * @return  the value of assistant_group_route.supper
	 * @mbggenerated
	 */
	public String getSupper() {
		return supper;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.supper
	 * @param supper  the value for assistant_group_route.supper
	 * @mbggenerated
	 */
	public void setSupper(String supper) {
		this.supper = supper == null ? null : supper.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.hotel_name
	 * @return  the value of assistant_group_route.hotel_name
	 * @mbggenerated
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.hotel_name
	 * @param hotelName  the value for assistant_group_route.hotel_name
	 * @mbggenerated
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName == null ? null : hotelName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.route_tip
	 * @return  the value of assistant_group_route.route_tip
	 * @mbggenerated
	 */
	public String getRouteTip() {
		return routeTip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.route_tip
	 * @param routeTip  the value for assistant_group_route.route_tip
	 * @mbggenerated
	 */
	public void setRouteTip(String routeTip) {
		this.routeTip = routeTip == null ? null : routeTip.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_route.route_desp
	 * @return  the value of assistant_group_route.route_desp
	 * @mbggenerated
	 */
	public String getRouteDesp() {
		return routeDesp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_route.route_desp
	 * @param routeDesp  the value for assistant_group_route.route_desp
	 * @mbggenerated
	 */
	public void setRouteDesp(String routeDesp) {
		this.routeDesp = routeDesp == null ? null : routeDesp.trim();
	}
}
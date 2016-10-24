package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class TransferOrderFamily implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5425004829646085422L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.order_id
	 * @mbggenerated
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.from_order_id
	 * @mbggenerated
	 */
	private Integer fromOrderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.from_order_family_id
	 * @mbggenerated
	 */
	private Integer fromOrderFamilyId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.leader_name
	 * @mbggenerated
	 */
	private String leaderName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.person_adult
	 * @mbggenerated
	 */
	private Integer personAdult;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.person_child
	 * @mbggenerated
	 */
	private Integer personChild;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.hotel_level
	 * @mbggenerated
	 */
	private String hotelLevel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.hotel_nums
	 * @mbggenerated
	 */
	private String hotelNums;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.transport_arrival
	 * @mbggenerated
	 */
	private String transportArrival;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.transport_middle
	 * @mbggenerated
	 */
	private String transportMiddle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.transport_leave
	 * @mbggenerated
	 */
	private String transportLeave;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order_family.remark
	 * @mbggenerated
	 */
	private String remark;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.id
	 * @return  the value of transfer_order_family.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.id
	 * @param id  the value for transfer_order_family.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.order_id
	 * @return  the value of transfer_order_family.order_id
	 * @mbggenerated
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.order_id
	 * @param orderId  the value for transfer_order_family.order_id
	 * @mbggenerated
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.from_order_id
	 * @return  the value of transfer_order_family.from_order_id
	 * @mbggenerated
	 */
	public Integer getFromOrderId() {
		return fromOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.from_order_id
	 * @param fromOrderId  the value for transfer_order_family.from_order_id
	 * @mbggenerated
	 */
	public void setFromOrderId(Integer fromOrderId) {
		this.fromOrderId = fromOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.from_order_family_id
	 * @return  the value of transfer_order_family.from_order_family_id
	 * @mbggenerated
	 */
	public Integer getFromOrderFamilyId() {
		return fromOrderFamilyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.from_order_family_id
	 * @param fromOrderFamilyId  the value for transfer_order_family.from_order_family_id
	 * @mbggenerated
	 */
	public void setFromOrderFamilyId(Integer fromOrderFamilyId) {
		this.fromOrderFamilyId = fromOrderFamilyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.leader_name
	 * @return  the value of transfer_order_family.leader_name
	 * @mbggenerated
	 */
	public String getLeaderName() {
		return leaderName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.leader_name
	 * @param leaderName  the value for transfer_order_family.leader_name
	 * @mbggenerated
	 */
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName == null ? null : leaderName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.person_adult
	 * @return  the value of transfer_order_family.person_adult
	 * @mbggenerated
	 */
	public Integer getPersonAdult() {
		return personAdult;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.person_adult
	 * @param personAdult  the value for transfer_order_family.person_adult
	 * @mbggenerated
	 */
	public void setPersonAdult(Integer personAdult) {
		this.personAdult = personAdult;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.person_child
	 * @return  the value of transfer_order_family.person_child
	 * @mbggenerated
	 */
	public Integer getPersonChild() {
		return personChild;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.person_child
	 * @param personChild  the value for transfer_order_family.person_child
	 * @mbggenerated
	 */
	public void setPersonChild(Integer personChild) {
		this.personChild = personChild;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.hotel_level
	 * @return  the value of transfer_order_family.hotel_level
	 * @mbggenerated
	 */
	public String getHotelLevel() {
		return hotelLevel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.hotel_level
	 * @param hotelLevel  the value for transfer_order_family.hotel_level
	 * @mbggenerated
	 */
	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel == null ? null : hotelLevel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.hotel_nums
	 * @return  the value of transfer_order_family.hotel_nums
	 * @mbggenerated
	 */
	public String getHotelNums() {
		return hotelNums;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.hotel_nums
	 * @param hotelNums  the value for transfer_order_family.hotel_nums
	 * @mbggenerated
	 */
	public void setHotelNums(String hotelNums) {
		this.hotelNums = hotelNums == null ? null : hotelNums.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.transport_arrival
	 * @return  the value of transfer_order_family.transport_arrival
	 * @mbggenerated
	 */
	public String getTransportArrival() {
		return transportArrival;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.transport_arrival
	 * @param transportArrival  the value for transfer_order_family.transport_arrival
	 * @mbggenerated
	 */
	public void setTransportArrival(String transportArrival) {
		this.transportArrival = transportArrival == null ? null : transportArrival.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.transport_middle
	 * @return  the value of transfer_order_family.transport_middle
	 * @mbggenerated
	 */
	public String getTransportMiddle() {
		return transportMiddle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.transport_middle
	 * @param transportMiddle  the value for transfer_order_family.transport_middle
	 * @mbggenerated
	 */
	public void setTransportMiddle(String transportMiddle) {
		this.transportMiddle = transportMiddle == null ? null : transportMiddle.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.transport_leave
	 * @return  the value of transfer_order_family.transport_leave
	 * @mbggenerated
	 */
	public String getTransportLeave() {
		return transportLeave;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.transport_leave
	 * @param transportLeave  the value for transfer_order_family.transport_leave
	 * @mbggenerated
	 */
	public void setTransportLeave(String transportLeave) {
		this.transportLeave = transportLeave == null ? null : transportLeave.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order_family.remark
	 * @return  the value of transfer_order_family.remark
	 * @mbggenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order_family.remark
	 * @param remark  the value for transfer_order_family.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}
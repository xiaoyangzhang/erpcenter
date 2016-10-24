package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class AssistantGroupOrderGuest  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78440347155195751L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.group_id
	 * @mbggenerated
	 */
	private Integer groupId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.order_id
	 * @mbggenerated
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.name
	 * @mbggenerated
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.type
	 * @mbggenerated
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.certificate_num
	 * @mbggenerated
	 */
	private String certificateNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.gender
	 * @mbggenerated
	 */
	private Integer gender;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.mobile
	 * @mbggenerated
	 */
	private String mobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.native_place
	 * @mbggenerated
	 */
	private String nativePlace;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.age
	 * @mbggenerated
	 */
	private Integer age;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.career
	 * @mbggenerated
	 */
	private String career;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.is_single_room
	 * @mbggenerated
	 */
	private Integer isSingleRoom;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.is_leader
	 * @mbggenerated
	 */
	private Integer isLeader;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.remark
	 * @mbggenerated
	 */
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column assistant_group_order_guest.is_guide
	 * @mbggenerated
	 */
	private Integer isGuide;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.id
	 * @return  the value of assistant_group_order_guest.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.id
	 * @param id  the value for assistant_group_order_guest.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.group_id
	 * @return  the value of assistant_group_order_guest.group_id
	 * @mbggenerated
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.group_id
	 * @param groupId  the value for assistant_group_order_guest.group_id
	 * @mbggenerated
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.order_id
	 * @return  the value of assistant_group_order_guest.order_id
	 * @mbggenerated
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.order_id
	 * @param orderId  the value for assistant_group_order_guest.order_id
	 * @mbggenerated
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.name
	 * @return  the value of assistant_group_order_guest.name
	 * @mbggenerated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.name
	 * @param name  the value for assistant_group_order_guest.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.type
	 * @return  the value of assistant_group_order_guest.type
	 * @mbggenerated
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.type
	 * @param type  the value for assistant_group_order_guest.type
	 * @mbggenerated
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.certificate_num
	 * @return  the value of assistant_group_order_guest.certificate_num
	 * @mbggenerated
	 */
	public String getCertificateNum() {
		return certificateNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.certificate_num
	 * @param certificateNum  the value for assistant_group_order_guest.certificate_num
	 * @mbggenerated
	 */
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum == null ? null : certificateNum.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.gender
	 * @return  the value of assistant_group_order_guest.gender
	 * @mbggenerated
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.gender
	 * @param gender  the value for assistant_group_order_guest.gender
	 * @mbggenerated
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.mobile
	 * @return  the value of assistant_group_order_guest.mobile
	 * @mbggenerated
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.mobile
	 * @param mobile  the value for assistant_group_order_guest.mobile
	 * @mbggenerated
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.native_place
	 * @return  the value of assistant_group_order_guest.native_place
	 * @mbggenerated
	 */
	public String getNativePlace() {
		return nativePlace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.native_place
	 * @param nativePlace  the value for assistant_group_order_guest.native_place
	 * @mbggenerated
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace == null ? null : nativePlace.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.age
	 * @return  the value of assistant_group_order_guest.age
	 * @mbggenerated
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.age
	 * @param age  the value for assistant_group_order_guest.age
	 * @mbggenerated
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.career
	 * @return  the value of assistant_group_order_guest.career
	 * @mbggenerated
	 */
	public String getCareer() {
		return career;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.career
	 * @param career  the value for assistant_group_order_guest.career
	 * @mbggenerated
	 */
	public void setCareer(String career) {
		this.career = career == null ? null : career.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.is_single_room
	 * @return  the value of assistant_group_order_guest.is_single_room
	 * @mbggenerated
	 */
	public Integer getIsSingleRoom() {
		return isSingleRoom;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.is_single_room
	 * @param isSingleRoom  the value for assistant_group_order_guest.is_single_room
	 * @mbggenerated
	 */
	public void setIsSingleRoom(Integer isSingleRoom) {
		this.isSingleRoom = isSingleRoom;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.is_leader
	 * @return  the value of assistant_group_order_guest.is_leader
	 * @mbggenerated
	 */
	public Integer getIsLeader() {
		return isLeader;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.is_leader
	 * @param isLeader  the value for assistant_group_order_guest.is_leader
	 * @mbggenerated
	 */
	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.remark
	 * @return  the value of assistant_group_order_guest.remark
	 * @mbggenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.remark
	 * @param remark  the value for assistant_group_order_guest.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column assistant_group_order_guest.is_guide
	 * @return  the value of assistant_group_order_guest.is_guide
	 * @mbggenerated
	 */
	public Integer getIsGuide() {
		return isGuide;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column assistant_group_order_guest.is_guide
	 * @param isGuide  the value for assistant_group_order_guest.is_guide
	 * @mbggenerated
	 */
	public void setIsGuide(Integer isGuide) {
		this.isGuide = isGuide;
	}
}
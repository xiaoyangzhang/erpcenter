package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author qindz
 * 散客团订单打印po类
 *
 */
public class GroupOrderPrintPo implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6306982936380941780L;
	//接站方式
	private String receiveMode ;
	//组团社名称
	private String supplierName;
	//客源
	private String place ;
	//销售计调
	private String saleOperatorName ;
	//人数统计
	private String personNum ;
	//客人信息
	private String guestInfo ;
	//酒店信息
	private String hotelInfo ;
	//送机信息
	private String airPickup ;
	//接机信息
	private String airOff ;
	//订单备注
	private String remark ;

	private String remarkInternal ;
	
	//电话
	private String phone ;
	//酒店星级
	private String hotelLevel ;
	//房量
	private String hotelNum ;
	//身份证号
	private String certificateNums ;
	//客人统计
	private String guesStatic ;
	//线路类型
	private String trans ;


	public String getRemarkInternal() {
		return remarkInternal;
	}
	public void setRemarkInternal(String remarkInternal) {
		this.remarkInternal = remarkInternal;
	}
	private List<GroupOrderGuest> guests  ;
	private GroupOrder groupOrder;
	private List<GroupOrderTransport> orderTransports;
	private List<GroupRequirement> groupRequirements;
	
	public List<GroupRequirement> getGroupRequirements() {
		return groupRequirements;
	}
	public void setGroupRequirements(List<GroupRequirement> groupRequirements) {
		this.groupRequirements = groupRequirements;
	}
	public List<GroupOrderTransport> getOrderTransports() {
		return orderTransports;
	}
	public void setOrderTransports(List<GroupOrderTransport> orderTransports) {
		this.orderTransports = orderTransports;
	}
	public GroupOrder getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
	public List<GroupOrderGuest> getGuests() {
		return new ArrayList<GroupOrderGuest>();
	}
	public void setGuests(List<GroupOrderGuest> guests) {
		this.guests = guests;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getHotelNum() {
		return hotelNum;
	}
	public void setHotelNum(String hotelNum) {
		this.hotelNum = hotelNum;
	}
	public String getGuesStatic() {
		return guesStatic;
	}
	public void setGuesStatic(String guesStatic) {
		this.guesStatic = guesStatic;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHotelLevel() {
		return hotelLevel;
	}
	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel;
	}
	public String getCertificateNums() {
		return certificateNums;
	}
	public void setCertificateNums(String certificateNums) {
		this.certificateNums = certificateNums;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSaleOperatorName() {
		return saleOperatorName;
	}
	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}
	public String getPersonNum() {
		return personNum;
	}
	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}
	public String getGuestInfo() {
		return guestInfo;
	}
	public void setGuestInfo(String guestInfo) {
		this.guestInfo = guestInfo;
	}
	public String getHotelInfo() {
		return hotelInfo;
	}
	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
	}
	public String getAirPickup() {
		return airPickup;
	}
	public void setAirPickup(String airPickup) {
		this.airPickup = airPickup;
	}
	public String getAirOff() {
		return airOff;
	}
	public void setAirOff(String airOff) {
		this.airOff = airOff;
	}
}

package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.util.Date;

public class GroupBookingInfo implements Serializable {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 5864988127960197212L;
	private Integer groupId;
	private Integer orderId;
	private String receiveMode;
	private String groupCode;
	private Date dateStart;
	private Date dateEnd;
	private Integer groupMode;
	private String productBrandName;
	private String productName;
	private Integer totalAdult;
	private Integer totalChild;
	private Integer totalGuide;
	private String operatorName;
	private String groupStatus;
	private Integer groupState;
	private Integer hotelCnt;
	private Integer eatCnt;
	private Integer sightCnt;
	private Integer carCnt;
	private Integer inCnt;
	private Integer outCnt;
	private Integer airCnt;
	private Integer trainCnt;
	private Integer insuranceCnt;
	private Integer shopCnt;
	private String guideNames;
	private String[] guideNameArr;
	private String deliveryNames;
	private String[] deliveryNameArr;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getTotalAdult() {
		return totalAdult;
	}
	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}
	public Integer getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}
	public Integer getTotalGuide() {
		return totalGuide;
	}
	public void setTotalGuide(Integer totalGuide) {
		this.totalGuide = totalGuide;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
//	public String getGroupStatus() {
//		return groupStatus;
//	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	public Integer getHotelCnt() {
		return hotelCnt;
	}
	public void setHotelCnt(Integer hotelCnt) {
		this.hotelCnt = hotelCnt;
	}
	public Integer getEatCnt() {
		return eatCnt;
	}
	public void setEatCnt(Integer eatCnt) {
		this.eatCnt = eatCnt;
	}
	public Integer getCarCnt() {
		return carCnt;
	}
	public void setCarCnt(Integer carCnt) {
		this.carCnt = carCnt;
	}
	public Integer getInCnt() {
		return inCnt;
	}
	public void setInCnt(Integer inCnt) {
		this.inCnt = inCnt;
	}
	public Integer getOutCnt() {
		return outCnt;
	}
	public void setOutCnt(Integer outCnt) {
		this.outCnt = outCnt;
	}
	public Integer getAirCnt() {
		return airCnt;
	}
	public void setAirCnt(Integer airCnt) {
		this.airCnt = airCnt;
	}
	public Integer getTrainCnt() {
		return trainCnt;
	}
	public void setTrainCnt(Integer trainCnt) {
		this.trainCnt = trainCnt;
	}
	public Integer getInsuranceCnt() {
		return insuranceCnt;
	}
	public void setInsuranceCnt(Integer insuranceCnt) {
		this.insuranceCnt = insuranceCnt;
	}
	public Integer getShopCnt() {
		return shopCnt;
	}
	public void setShopCnt(Integer shopCnt) {
		this.shopCnt = shopCnt;
	}
	public String getGuideNames() {
		return guideNames;
	}
	public void setGuideNames(String guideNames) {
		this.guideNames = guideNames;
	}
	public String getDeliveryNames() {
		return deliveryNames;
	}
	public void setDeliveryNames(String deliveryNames) {
		this.deliveryNames = deliveryNames;
	}
	public Integer getSightCnt() {
		return sightCnt;
	}
	public void setSightCnt(Integer sightCnt) {
		this.sightCnt = sightCnt;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	public String[] getGuideNameArr() {
		if(guideNames!=null && !guideNames.equals("")){
			return guideNames.split(",");
		}
		return guideNameArr;
	}
	public String[] getDeliveryNameArr() {
		if(deliveryNames!=null && !deliveryNames.equals("")){
			return deliveryNames.split(",");
		}
		return deliveryNameArr;
	}
	public String getGroupStatus() {
		if(groupState!=null){
			switch(groupState.intValue()){
			case 0:
				return "未确认";
			case 1:
				if(new Date().before(dateStart)){
					return "已确认（未出团）";
				}else{
					if(dateEnd == null){
						return "已确认";
					}if(dateEnd.after(new Date())){
						return "已确认（行程中）";
					}else{
						return "已确认（已离开）";
					}
				}
			case 3:
				return "已审核";
			case 4:
				return "已归档";
			default:
				return "已删除";
			}
		}
		return "";
	}
}

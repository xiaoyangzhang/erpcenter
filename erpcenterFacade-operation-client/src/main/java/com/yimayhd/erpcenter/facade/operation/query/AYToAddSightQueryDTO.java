package com.yimayhd.erpcenter.facade.operation.query;

import java.io.Serializable;

public class AYToAddSightQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5858026834508567306L;
	
	
	private Integer groupId;
	
	private String hotelTypeCode ;
	
	private String resTypeCode;
	
	private String carTypeCode;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getHotelTypeCode() {
		return hotelTypeCode;
	}

	public void setHotelTypeCode(String hotelTypeCode) {
		this.hotelTypeCode = hotelTypeCode;
	}

	public String getResTypeCode() {
		return resTypeCode;
	}

	public void setResTypeCode(String resTypeCode) {
		this.resTypeCode = resTypeCode;
	}

	public String getCarTypeCode() {
		return carTypeCode;
	}

	public void setCarTypeCode(String carTypeCode) {
		this.carTypeCode = carTypeCode;
	}
	
	
}

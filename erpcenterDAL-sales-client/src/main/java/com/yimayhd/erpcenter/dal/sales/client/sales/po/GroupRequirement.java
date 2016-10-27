package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.Date;

public class GroupRequirement implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer groupId;

    private Integer orderId;

    private Integer supplierType;

    private String requireDate;
    
    private String requireDateTo;

    private String area;

    private String hotelLevel;
    
    private String hotelLevelName;
    private Integer countSingleRoom;

    private Integer countDoubleRoom;

    private Integer countTripleRoom;
    
    private Integer peiFang;
    private Integer extraBed ;

    private String modelNum;

    private Integer countSeat;

    private String ageLimit;

    private String classNo;

    private String cityDeparture;

    private String cityArrival;

    private Integer countRequire;

    private String language;

    private String gender;

    private String remark;

    private Integer creatorId;

    private String creatorName;

    private Long createTime;
    
    private String	 receiveMode;//接站牌

    public String getReceiveMode() {
		return receiveMode;
	}

	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}

	private String nameFull;
    public String getNameFull() {
		return nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
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

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }


    public String getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(String requireDate) {
		this.requireDate = requireDate;
	}
	
	

	public String getRequireDateTo() {
		return requireDateTo;
	}

	public void setRequireDateTo(String requireDateTo) {
		this.requireDateTo = requireDateTo;
	}

	public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(String hotelLevel) {
        this.hotelLevel = hotelLevel == null ? null : hotelLevel.trim();
    }

    
    public String getHotelLevelName() {
		return hotelLevelName;
	}

	public void setHotelLevelName(String hotelLevelName) {
		this.hotelLevelName = hotelLevelName;
	}

	public Integer getCountSingleRoom() {
        return countSingleRoom;
    }

    public void setCountSingleRoom(Integer countSingleRoom) {
        this.countSingleRoom = countSingleRoom;
    }

    public Integer getCountDoubleRoom() {
        return countDoubleRoom;
    }

    public void setCountDoubleRoom(Integer countDoubleRoom) {
        this.countDoubleRoom = countDoubleRoom;
    }

    public Integer getCountTripleRoom() {
        return countTripleRoom;
    }

    public void setCountTripleRoom(Integer countTripleRoom) {
        this.countTripleRoom = countTripleRoom;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum == null ? null : modelNum.trim();
    }

    public Integer getCountSeat() {
        return countSeat;
    }

    public void setCountSeat(Integer countSeat) {
        this.countSeat = countSeat;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit == null ? null : ageLimit.trim();
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo == null ? null : classNo.trim();
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture == null ? null : cityDeparture.trim();
    }

    public String getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival == null ? null : cityArrival.trim();
    }

    public Integer getCountRequire() {
        return countRequire;
    }

    public void setCountRequire(Integer countRequire) {
        this.countRequire = countRequire;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getRemark() {
        return remark;
    }
    

    public Integer getPeiFang() {
		return peiFang;
	}

	public void setPeiFang(Integer peiFang) {
		this.peiFang = peiFang;
	}

	public Integer getExtraBed() {
		return extraBed;
	}

	public void setExtraBed(Integer extraBed) {
		this.extraBed = extraBed;
	}

	public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
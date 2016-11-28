package com.yimayhd.erpcenter.dal.sales.client.sales.po;


import java.io.Serializable;

import com.yimayhd.erpcenter.common.util.LogFieldAnno;

public class GroupOrderGuest implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@LogFieldAnno(isKey = true)
	private Integer id;

    private Integer orderId;
    @LogFieldAnno(description="客人姓名", delOrIns = true)
    private String name;
    @LogFieldAnno(description="类别",needExtDescription = "[{\"key\":\"1\", \"value\":\"成人\"},{\"key\":\"2\", \"value\":\"儿童\"},{\"key\":\"3\", \"value\":\"全陪\"}]")
    private Integer type;

    private Integer certificateTypeId;
    @LogFieldAnno(description="证件号", delOrIns = true)
    private String certificateNum;
    @LogFieldAnno(description="姓别", delOrIns = true,needExtDescription = "[{\"key\":\"0\", \"value\":\"女\"},{\"key\":\"1\", \"value\":\"男\"}]")
    private Integer gender;
    @LogFieldAnno(description="电话", delOrIns = true)
    private String mobile;
    @LogFieldAnno(description="籍贯")
    private String nativePlace;
    @LogFieldAnno(description="年龄")
    private Integer age;
    @LogFieldAnno(description="职业")
    private String career;
    @LogFieldAnno(description="单房差")
    private Integer isSingleRoom;
    @LogFieldAnno(description="是否领队")
    private Integer isLeader;
    @LogFieldAnno(description="备注", delOrIns = true)
    private String remark;

    private Long createTime;
    private Integer man;
    private Integer woman;
    
    private String userName;
    private String ticketTime;
    private String groupCode;
    private Integer groupId;
    
    
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(String ticketTime) {
		this.ticketTime = ticketTime;
	}


	public Integer getMan() {
		return man;
	}


	public void setMan(Integer man) {
		this.man = man;
	}

	public Integer getWoman() {
		return woman;
	}


	public void setWoman(Integer woman) {
		this.woman = woman;
	}

	/**
     * 历史参团次数、数据库没有该字段
     */
    private Integer historyNum ;
    /**
     * 是否全陪
     */
    private Integer isGuide ;
    
    private boolean editType;
	
	public Integer getIsGuide() {
		return isGuide;
	}

	public void setIsGuide(Integer isGuide) {
		this.isGuide = isGuide;
	}

	public Integer getHistoryNum() {
		return historyNum;
	}

	public void setHistoryNum(Integer historyNum) {
		this.historyNum = historyNum;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(Integer certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
    }

    public Integer getIsSingleRoom() {
        return isSingleRoom;
    }

    public void setIsSingleRoom(Integer isSingleRoom) {
        this.isSingleRoom = isSingleRoom;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public boolean isEditType() {
		return editType;
	}

	public void setEditType(boolean editType) {
		this.editType = editType;
	}

	
    
}
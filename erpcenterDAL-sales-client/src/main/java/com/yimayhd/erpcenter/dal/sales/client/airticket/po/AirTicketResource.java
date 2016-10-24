package com.yimayhd.erpcenter.dal.sales.client.airticket.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AirTicketResource implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2706210423523744842L;

	private Integer id;

    private Integer bizId;

    private String resourceNumber;

    private Date startDate;
    private Date endDate;
    private Date depDate;

    private String depCity;

    private Integer ticketSupplierId;

    private String ticketSupplier;

    private String ticketOrderNum;

    private BigDecimal buyPrice;
    private BigDecimal price;

    private BigDecimal advancedDeposit;

    private Integer totalNumber;
    private Integer appliedNumber;

    private String status;

    private String isDel;

    private Integer createrId;

    private String createrName;

    private Integer operatorId;

    private String operatorName;

    private Date addTime;

    private Date updateTime;

    private String comment;
    
    private String contact;
    
    private String contactMobile;
    
    private String contactTel;
    
    private String contactFax;
    
    private String seatType;
    
    private Integer contactId;
    
    private Date endIssueTime;
    
    private String lineName;
    private String type;
    
    /**
     * 业务主键
     * @return
     */
    public Integer getId() {
        return id;
        }
    /**
     * 业务主键
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 商家ID
     * @return
     */
    public Integer getBizId() {
        return bizId;
    }
    /**
     * 商家ID
     * @param bizId
     */
    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }
    /**
     * 采购单号
     * @return
     */
    public String getResourceNumber() {
        return resourceNumber;
    }
    /**
     * 采购单号
     * @param resourceNumber
     */
    public void setResourceNumber(String resourceNumber) {
        this.resourceNumber = resourceNumber == null ? null : resourceNumber.trim();
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
        this.depCity = depCity == null ? null : depCity.trim();
    }
    /**
     * 机票商家ID
     * @return
     */
    public Integer getTicketSupplierId() {
        return ticketSupplierId;
    }
    /**
     * 机票商家ID
     * @param ticketSupplierId
     */
    public void setTicketSupplierId(Integer ticketSupplierId) {
        this.ticketSupplierId = ticketSupplierId;
    }
    /**
     * 机票商家名称
     * @return
     */
    public String getTicketSupplier() {
        return ticketSupplier;
    }
    /**
     * 机票商家名称
     * @param ticketSupplier
     */
    public void setTicketSupplier(String ticketSupplier) {
        this.ticketSupplier = ticketSupplier == null ? null : ticketSupplier.trim();
    }
    /**
     * 机票订单号
     * @return
     */
    public String getTicketOrderNum() {
        return ticketOrderNum;
    }
    /**
     * 机票订单号
     * @param ticketOrderNum
     */
    public void setTicketOrderNum(String ticketOrderNum) {
        this.ticketOrderNum = ticketOrderNum == null ? null : ticketOrderNum.trim();
    }
    /**
     * 机票价格（单价）
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * 机票价格（单价）
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }	
    /**
     * 押金
     * @return
     */
    public BigDecimal getAdvancedDeposit() {
        return advancedDeposit;
    }
    /**
     * 押金
     * @param advancedDeposit
     */
    public void setAdvancedDeposit(BigDecimal advancedDeposit) {
        this.advancedDeposit = advancedDeposit;
    }
    /**
     * 总张数
     * @return
     */
    public Integer getTotalNumber() {
        return totalNumber;
    }
    /**
     * 总张数
     * @param totalNumber
     */
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
    /**
     * 状态：on=已上架，off=已下架
     * @return
     */
    public String getStatus() {
        return status;
    }
    /**
     *  状态：on=已上架，off=已下架
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    /**
     * 是否删除：N未删除，Y已删除
     * @return
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * 是否删除：N未删除，Y已删除
     * @param isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
    /**
     * 创建人ID
     * @return
     */
    public Integer getCreaterId() {
        return createrId;
    }
    /**
     * 创建人ID
     * @param createrId
     */
    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }
    /**
     * 创建人
     * @return
     */
    public String getCreaterName() {
        return createrName;
    }
    /**
     * 创建人
     * @param createrName
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }
    /**
     * 最后更新人ID
     * @return
     */
    public Integer getOperatorId() {
        return operatorId;
    }
    /**
     * 最后更新人ID
     * @param operatorId
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
    /**
     * 最后更新人
     * @return
     */
    public String getOperatorName() {
        return operatorName;
    }
    /**
     * 最后更新人
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }
    /**
     * 创建时间
     * @return
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 创建时间
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    /**
     * 更新时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 更新时间
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 票务人员针对该资源的备注
     * @return
     */
    public String getComment() {
        return comment;
    }
    /**
     * 票务人员针对该资源的备注
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
    /**
     * 余票数
     * @return
     */
	public Integer getAvailableNumber() {
		return totalNumber-appliedNumber;
	}

	/**
	 * 联系人
	 * @return
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 联系人
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 联系人手机
	 * @return
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * 联系人手机
	 * @param contactMobile
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * 联系人电话
	 * @return
	 */
	public String getContactTel() {
		return contactTel;
	}
	/**
	 * 联系人电话
	 * @param contactTel
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	/**
	 * 传真
	 * @return
	 */
	public String getContactFax() {
		return contactFax;
	}
	/**
	 * 传真
	 * @param contactFax
	 */
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
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
	/**
	 * 联系人ID
	 * @return
	 */
	public Integer getContactId() {
		return contactId;
	}
	/**
	 * 联系人ID
	 * @param contactId
	 */
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	/**
	 * 最晚出票时间
	 * @return
	 */
	public Date getEndIssueTime() {
		return endIssueTime;
	}
	/**
	 * 最晚出票时间
	 * @param endIssueTime
	 */
	public void setEndIssueTime(Date endIssueTime) {
		this.endIssueTime = endIssueTime;
	}
	/**
	 * 航线名称
	 * @return
	 */
	public String getLineName() {
		return lineName;
	}
	/**
	 * 航线名称
	 * @param lineName
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getAppliedNumber() {
		return appliedNumber;
	}
	public void setAppliedNumber(Integer appliedNumber) {
		this.appliedNumber = appliedNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
package com.yimayhd.erpcenter.dal.sales.client.airticket.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AirTicketOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3057567406379277133L;

	private Integer id;

    private Integer bizId;

    private Integer resourceId;

    private Integer requestId;

    private Integer airLineId;

    private Integer guestId;

    private String isDel;

    private Integer operatorId;

    private Date addTime;

    private Date updateTime;
    
    private BigDecimal price;
    
    private String comment;
    
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
     * 机票资源ID
     * @return
     */
    public Integer getResourceId() {
        return resourceId;
    }
    /**
     * 机票资源ID
     * @param resourceId
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    /**
     * 机票请求ID
     * @return
     */
    public Integer getRequestId() {
        return requestId;
    }
    /**
     * 机票请求ID
     * @param requestId
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
    /**
     * 机票线路ID
     * @return
     */
    public Integer getAirLineId() {
        return airLineId;
    }
    /**
     * 机票线路ID
     * @param airLineId
     */
    public void setAirLineId(Integer airLineId) {
        this.airLineId = airLineId;
    }
    /**
     * 旅客ID
     * @return
     */
    public Integer getGuestId() {
        return guestId;
    }
    /**
     * 旅客ID
     * @param guestId
     */
    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }
    /**
     * 是否删除（'Y':已删除；'N'：未删除）
     * @return
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * 是否删除（'Y':已删除；'N'：未删除）
     * @param isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
    /**
     * 创建人ID
     * @return
     */
    public Integer getOperatorId() {
        return operatorId;
    }
    /**
     * 创建人ID
     * @param operatorId
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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
     * 价格
     * @return
     */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 价格
	 * @param price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
    
}
package com.yimayhd.erpcenter.dal.sales.client.airticket.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AirTicketRequest implements Serializable{

	private static final long serialVersionUID = 6371737658989027518L;
	
	private Integer id;

    private Integer bizId;

    private Integer resourceId;

    private String resourceNumber;

    private Integer groupOrderId;

    private String status;

    private String isDel;

    private Integer createrId;

    private String createrName;

    private Integer operatorId;

    private String operatorName;

    private Date addTime;

    private Date updateTime;

    private String comment;
    private String remark;
    private AirTicketResource resource;
    
    private List<AirTicketOrder> orderList;
    private String paymentType;
    private Integer bookingSupplierId;
    private Integer guestNumber;
    
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
     * 销售订单ID
     * @return
     */
    public Integer getGroupOrderId() {
        return groupOrderId;
    }
    /**
     * 销售订单ID
     * @param groupOrderId
     */
    public void setGroupOrderId(Integer groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

    /**
     * 状态
     *  ARRANGING:销售提交后等待票务安排；
	 *	CONFIRMING:票务安排后等待销售确认或修改；
	 *	REJECTED:票务退回销售的申请后等待销售修改；
	 *	ISSUING:销售确认后等待票务出票；
	 *	ISSUED:票务出票后标记为已出票
     * @return
     */
    public String getStatus() {
        return status;
    }
    /**
     * 状态
     *  ARRANGING:销售提交后等待票务安排；
	 *	CONFIRMING:票务安排后等待销售确认或修改；
	 *	REJECTED:票务退回销售的申请后等待销售修改；
	 *	ISSUING:销售确认后等待票务出票；
	 *	ISSUED:票务出票后标记为已出票
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    /**
     * 是否删除    N未删除，Y已删除
     * @return
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * 是否删除    N未删除，Y已删除
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
     * 创建人姓名
     * @return
     */
    public String getCreaterName() {
        return createrName;
    }
    /**
     * 创建人姓名
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
     * 最后更新人姓名
     * @return
     */
    public String getOperatorName() {
        return operatorName;
    }
    /**
     * 最后更新人姓名
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
     * 销售人员针对该申请的备注
     * @return
     */
    public String getComment() {
        return comment;
    }
    /**
     * 销售人员针对该申请的备注
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
    /**
     * 订单集合
     * @return
     */
	public List<AirTicketOrder> getOrderList() {
		return orderList;
	}
	/**
	 * 订单集合
	 * @param orderList
	 */
	public void setOrderList(List<AirTicketOrder> orderList) {
		this.orderList = orderList;
	}
	/**
	 * 机票资源
	 * @return
	 */
	public AirTicketResource getResource() {
		return resource;
	}
	/**
	 * 机票资源
	 * @param resource
	 */
	public void setResource(AirTicketResource resource) {
		this.resource = resource;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Integer getBookingSupplierId() {
		return bookingSupplierId;
	}
	public void setBookingSupplierId(Integer bookingSupplierId) {
		this.bookingSupplierId = bookingSupplierId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getGuestNumber() {
		return guestNumber;
	}
	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}
}
package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingDeliveryPrice implements Serializable  {
    private Integer id;

    private Integer bookingId;

    private String itemName;

    private String remark;

    private BigDecimal unitPrice;

    private BigDecimal numTimes;

    private BigDecimal numPerson;

    private BigDecimal totalPrice;

    private Integer rowState;

    private Integer creatorId;

    private String creatorName;

    private Long createTime;
    
    //地接费用查询用
	private String groupCode ;
    private Date dateStart ;
    private Date dateEnd ;
    private String operatorIds ;
    private String operatorName ;
    private Integer personAdult ;
    private Integer personChild ;
    private Integer personGuide ;
    private String supplierName ;
    private String startTime ;
    private String endTime ;
    private Integer groupMode ;
    private Integer page =1;
    private Integer pageSize ;
    private String groupIds;
    private Integer groupId;
    private String saleOperatorIds;
	private String productBrandName;
    private String productName;
    
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
		this.productName = productName.trim();
	}


    
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}

	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getGroupMode() {
		return groupMode;
	}

	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode.trim();
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

	public String getOperatorIds() {
		return operatorIds;
	}

	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getPersonAdult() {
		return personAdult;
	}

	public void setPersonAdult(Integer personAdult) {
		this.personAdult = personAdult;
	}

	public Integer getPersonChild() {
		return personChild;
	}

	public void setPersonChild(Integer personChild) {
		this.personChild = personChild;
	}

	public Integer getPersonGuide() {
		return personGuide;
	}

	public void setPersonGuide(Integer personGuide) {
		this.personGuide = personGuide;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getNumTimes() {
        return numTimes;
    }

    public void setNumTimes(BigDecimal numTimes) {
        this.numTimes = numTimes;
    }

    public BigDecimal getNumPerson() {
        return numPerson;
    }

    public void setNumPerson(BigDecimal numPerson) {
        this.numPerson = numPerson;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRowState() {
        return rowState;
    }

    public void setRowState(Integer rowState) {
        this.rowState = rowState;
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
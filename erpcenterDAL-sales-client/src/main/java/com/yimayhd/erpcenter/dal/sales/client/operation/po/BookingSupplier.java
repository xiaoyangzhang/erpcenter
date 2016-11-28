package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.common.util.LogFieldAnno;

public class BookingSupplier implements Serializable{
	@LogFieldAnno(isKey = true)
    private Integer id;
    private Integer groupId;
    private Integer supplierType;
    private Integer subType;
    private Integer supplierId;
    @LogFieldAnno(description="订单号", delOrIns = true)
    private String bookingNo;
    @LogFieldAnno(description="联系人")
    private String contact;
    @LogFieldAnno(description="联系人电话")
    private String contactTel;
    @LogFieldAnno(description="联系人传真")
    private String contactFax;
    @LogFieldAnno(description="联系人手机")
    private String contactMobile;
    @LogFieldAnno(description="结算方式")
    private String cashType;
    private Byte isDefault;
    @LogFieldAnno(description="备注")
    private String remark;
    @LogFieldAnno(description="订单金额")
    private BigDecimal total;
    private BigDecimal ftotal;
    private Integer fid;
    @LogFieldAnno(description="付款金额")
    private BigDecimal totalCash;
    @LogFieldAnno(description="订单状态")
    private Integer stateBooking;
    @LogFieldAnno(description="审核状态")
    private Integer stateFinance;
    private Integer userId;
    private String userName;
    private String pstatus;
    private String payusername;
    private Date paytime;
	private Integer guideId;
    private Integer bookingId;
	private Long createTime;
	@LogFieldAnno(description="商家名称", delOrIns = true)
    private String supplierName;
	@LogFieldAnno(description="预订日期")
    private Date bookingDate;
    private String operatorName;//计调名称
    private List<String> supplierDetail;
    private BigDecimal operatePrice ; //计调价
    private Integer orderId;//散客团机票订单id
   
    private String hotelNumStatic ; //酒店下房间数统计
    private String cityName ;//酒店所在城市
    @LogFieldAnno(description="审核人")
    private String auditUser;
    private Integer auditUserId;
    private Date auditTime;
    
	private BigDecimal financeTotal;
    
    private Integer payStatus;
    private String guideInfo;
    private List<BookingSupplierDetail> detailList;
    private Boolean canDelete;
    private String guestIds;
    
    
    public String getGuestIds() {
		return guestIds;
	}

	public void setGuestIds(String guestIds) {
		this.guestIds = guestIds;
	}

	public String getGuideInfo() {
		return guideInfo;
	}

	public void setGuideInfo(String guideInfo) {
		this.guideInfo = guideInfo;
	}

	public BigDecimal getFinanceTotal() {
		return financeTotal;
	}

	public void setFinanceTotal(BigDecimal financeTotal) {
		this.financeTotal = financeTotal;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
    
    public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getPayusername() {
		return payusername;
	}

	public void setPayusername(String payusername) {
		this.payusername = payusername;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public String getPstatus() {
		return pstatus;
	}

	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHotelNumStatic() {
		return hotelNumStatic;
	}

	public void setHotelNumStatic(String hotelNumStatic) {
		this.hotelNumStatic = hotelNumStatic;
	}

	public Integer getOrderId() {
	return orderId;
}

	public void setOrderId(Integer orderId) {
	this.orderId = orderId;
}

	public BigDecimal getOperatePrice() {
		return operatePrice;
	}

	public void setOperatePrice(BigDecimal operatePrice) {
		this.operatePrice = operatePrice;
	}

	public List<String> getSupplierDetail() {
		return supplierDetail;
	}

	public void setSupplierDetail(List<String> supplierDetail) {
		this.supplierDetail = supplierDetail;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	
    
    public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Boolean getCanDelete() {    	
		return (this.stateFinance==null || this.stateFinance==0) && (this.totalCash == null || this.totalCash.compareTo(new BigDecimal(0))==0);
	}
    

	
	public BigDecimal getFtotal() {
		return ftotal;
	}

	public void setFtotal(BigDecimal ftotal) {
		this.ftotal = ftotal;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	

	public Integer getId() {
        return id;
    }

    public List<BookingSupplierDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<BookingSupplierDetail> detailList) {
		this.detailList = detailList;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax == null ? null : contactFax.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType == null ? null : cashType.trim();
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    
    public Integer getStateBooking() {
		return stateBooking;
	}

	public void setStateBooking(Integer stateBooking) {
		this.stateBooking = stateBooking;
	}

	public Integer getStateFinance() {
		return stateFinance;
	}

	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	
	/*public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}*/
    
}
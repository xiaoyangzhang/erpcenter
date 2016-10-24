package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BookingDelivery implements Serializable {
    private Integer id;

    private Integer groupId;

    private Integer supplierId;
    
    private String supplierName;
    
    private String supplierOrderNo;

    private String contact;

    private String contactTel;

    private String contactFax;

    private String contactMobile;

    private Integer personAdult;

    private Integer personChild;

    private Integer personGuide;

    private Date dateArrival;

    private String remark;

    private BigDecimal total;

    private BigDecimal totalCash;

    private Integer stateBooking;

    private Integer stateFinance;

    private Integer userId;
    
    private String userName;
    
    private Date bookingDate;

    private Long createTime;
    
    private List<BookingDeliveryOrder> orderList;
    
    private String orderListJson;
    
    private List<BookingDeliveryPrice> priceList;
    
    private String priceListJson;
    
    private List<BookingDeliveryRoute> routeList;
    
    private String routeListJson;

	private String auditUser;
	private Integer auditUserId;
	private Date auditTime;
//	private String guideInfo;
//	
//	public String getGuideInfo() {
//		return guideInfo;
//	}
//
//	public void setGuideInfo(String guideInfo) {
//		this.guideInfo = guideInfo;
//	}

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
    
    private Boolean canDelete;
    public Boolean getCanDelete() {    	
		return (this.stateFinance==null || this.stateFinance==0) && (this.totalCash == null || this.totalCash.compareTo(new BigDecimal(0))==0);
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

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierOrderNo() {
		return supplierOrderNo;
	}

	public void setSupplierOrderNo(String supplierOrderNo) {
		this.supplierOrderNo = supplierOrderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<BookingDeliveryOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<BookingDeliveryOrder> orderList) {
		this.orderList = orderList;
	}

	public List<BookingDeliveryPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<BookingDeliveryPrice> priceList) {
		this.priceList = priceList;
	}

	public List<BookingDeliveryRoute> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<BookingDeliveryRoute> routeList) {
		this.routeList = routeList;
	}

	public String getOrderListJson() {
		return orderListJson;
	}

	public void setOrderListJson(String orderListJson) {
		this.orderListJson = orderListJson;
	}

	public String getPriceListJson() {
		return priceListJson;
	}

	public void setPriceListJson(String priceListJson) {
		this.priceListJson = priceListJson;
	}

	public String getRouteListJson() {
		return routeListJson;
	}

	public void setRouteListJson(String routeListJson) {
		this.routeListJson = routeListJson;
	}
	
	
}
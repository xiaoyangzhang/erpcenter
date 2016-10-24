package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BookingGuide implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3456200574370221412L;

	private Integer id;

    private Integer groupId;

    private Integer guideId;
    private String guideName;
    private String guideMobile;
    private String bookingNo;
    private Byte isDefault;
    private Integer bookingDetailId;
    private Integer driverId;
    private String driverName;
    
    private String remark;

    private BigDecimal total;
    /**
     * 已经报账，计算出来的，不是表中字段
     */
    private BigDecimal totalCash;

    private Byte stateBooking;

    private Byte stateFinance;

    private Integer userId;

    private Long createTime;

    private String userName;
    
    private Date bookingDate;

	private List<FinanceCommission> comms; 		//佣金列表
	
	private String groupCode;					//团号
	
	private Integer personNum;					//带团人数
	
	private TourGroup group;
	
	private String bankAccount;
	
	private Integer stateLock;		


	public Integer getStateLock() {
		return stateLock;
	}

	public void setStateLock(Integer stateLock) {
		this.stateLock = stateLock;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public TourGroup getGroup() {
		return group;
	}

	public void setGroup(TourGroup group) {
		this.group = group;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public List<FinanceCommission> getComms() {
		return comms;
	}

	public void setComms(List<FinanceCommission> comms) {
		this.comms = comms;
	}
    
    public Integer getBookingDetailId() {
		return bookingDetailId;
	}

	public void setBookingDetailId(Integer bookingDetailId) {
		this.bookingDetailId = bookingDetailId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getGuideMobile() {
		return guideMobile;
	}

	public void setGuideMobile(String guideMobile) {
		this.guideMobile = guideMobile;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
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

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
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

    public Byte getStateBooking() {
        return stateBooking;
    }

    public void setStateBooking(Byte stateBooking) {
        this.stateBooking = stateBooking;
    }

    public Byte getStateFinance() {
        return stateFinance;
    }

    public void setStateFinance(Byte stateFinance) {
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

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	
    
}
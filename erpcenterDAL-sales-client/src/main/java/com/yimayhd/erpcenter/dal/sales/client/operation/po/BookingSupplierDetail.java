package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.erpcenter.common.util.LogFieldAnno;

public class BookingSupplierDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1767679585395221805L;
	@LogFieldAnno(isKey = true)
	private Integer id;
	private Integer bookingId;
    private Integer type1Id;
    @LogFieldAnno(description="类别", delOrIns = true)
    private String type1Name;

    private Integer type2Id;
    @LogFieldAnno(description="类别", delOrIns = true)
    private String type2Name;
    @LogFieldAnno(description="日期", delOrIns = true)
    private Date itemDate;//开始日期
    @LogFieldAnno(description="结束日期", delOrIns = true)
    private Date itemDateTo;//结束日期
    
    private String supplierName ; //字段冗余
	private String fangDiaoLuRu;
	private Integer resId;
	private Integer sumTicket;
    @LogFieldAnno(description = "计调费用", delOrIns = true)
    private Double carProfitTotal;
    @LogFieldAnno(description = "备注", delOrIns = true)
    private String carPayType;
    @LogFieldAnno(description = "其他利润", delOrIns = true)
    private Double carProfitTotal2;
    @LogFieldAnno(description = "备注", delOrIns = true)
    private String carPayType2;

    public Double getCarProfitTotal() {
        return carProfitTotal;
    }

    public void setCarProfitTotal(Double carProfitTotal) {
        this.carProfitTotal = carProfitTotal;
    }

    public String getCarPayType() {
        return carPayType;
    }

    public void setCarPayType(String carPayType) {
        this.carPayType = carPayType;
    }

    public Double getCarProfitTotal2() {
        return carProfitTotal2;
    }

    public void setCarProfitTotal2(Double carProfitTotal2) {
        this.carProfitTotal2 = carProfitTotal2;
    }

    public String getCarPayType2() {
        return carPayType2;
    }

    public void setCarPayType2(String carPayType2) {
        this.carPayType2 = carPayType2;
    }

    public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public Integer getSumTicket() {
		return sumTicket;
	}
	public void setSumTicket(Integer sumTicket) {
		this.sumTicket = sumTicket;
	}
	public String getFangDiaoLuRu() {
		return fangDiaoLuRu;
	}
	public void setFangDiaoLuRu(String fangDiaoLuRu) {
		this.fangDiaoLuRu = fangDiaoLuRu;
	}
    public Date getItemDateTo() {
		return itemDateTo;
	}

	public void setItemDateTo(Date itemDateTo) {
		this.itemDateTo = itemDateTo;
	}

	@LogFieldAnno(description="数量", delOrIns = true)
	private Double itemNum;
	@LogFieldAnno(description="价格", delOrIns = true)
    private Double itemPrice;
	@LogFieldAnno(description="减免数", delOrIns = true)
    private Double itemNumMinus;
	@LogFieldAnno(description="金额", delOrIns = true)
    private Double itemTotal;
	@LogFieldAnno(description="摘要")
    private String itemBrief;
	@LogFieldAnno(description="出发地", delOrIns = true)
    private String ticketDeparture;
	@LogFieldAnno(description="到达地", delOrIns = true)
    private String ticketArrival;
	@LogFieldAnno(description="班次", delOrIns = true)
    private String ticketFlight;

    private Integer driverId;
    @LogFieldAnno(description="司机", delOrIns = true)
    private String driverName;
    private String carLisence;
    private String driverTel;
    private Double totalNum;
    private Double saleItemPrice;
    
    
    public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Double getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
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

    public Integer getType1Id() {
        return type1Id;
    }

    public void setType1Id(Integer type1Id) {
        this.type1Id = type1Id;
    }

    public String getType1Name() {
        return type1Name;
    }

    public void setType1Name(String type1Name) {
        this.type1Name = type1Name == null ? null : type1Name.trim();
    }

    public Integer getType2Id() {
        return type2Id;
    }

    public void setType2Id(Integer type2Id) {
        this.type2Id = type2Id;
    }

    public String getType2Name() {
        return type2Name;
    }

    public void setType2Name(String type2Name) {
        this.type2Name = type2Name == null ? null : type2Name.trim();
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public Double getItemNum() {
        return itemNum;
    }

    public void setItemNum(Double itemNum) {
        this.itemNum = itemNum;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getItemNumMinus() {
        return itemNumMinus;
    }

    public void setItemNumMinus(Double itemNumMinus) {
        this.itemNumMinus = itemNumMinus;
    }

    public Double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getItemBrief() {
        return itemBrief;
    }

    public void setItemBrief(String itemBrief) {
        this.itemBrief = itemBrief == null ? null : itemBrief.trim();
    }

    public String getTicketDeparture() {
        return ticketDeparture;
    }

    public void setTicketDeparture(String ticketDeparture) {
        this.ticketDeparture = ticketDeparture == null ? null : ticketDeparture.trim();
    }

    public String getTicketArrival() {
        return ticketArrival;
    }

    public void setTicketArrival(String ticketArrival) {
        this.ticketArrival = ticketArrival == null ? null : ticketArrival.trim();
    }

    public String getTicketFlight() {
        return ticketFlight;
    }

    public void setTicketFlight(String ticketFlight) {
        this.ticketFlight = ticketFlight == null ? null : ticketFlight.trim();
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getCarLisence() {
		return carLisence;
	}

	public void setCarLisence(String carLisence) {
		this.carLisence = carLisence;
	}

	public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }
	public Double getSaleItemPrice() {
		return saleItemPrice;
	}
	public void setSaleItemPrice(Double saleItemPrice) {
		this.saleItemPrice = saleItemPrice;
	}
}
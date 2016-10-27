package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookingShopDetail implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1080105903637191444L;

	private Integer id;

    private Integer bookingId;

    private Integer goodsId;

    private String goodsName;

    private String goodsType;

    private Byte repayType;
    private Byte type;
    private BigDecimal repayVal;

    private BigDecimal repayTotal;

    private Integer buyNum;

    private BigDecimal buyPrice;

    private BigDecimal buyTotal;

    private Long createTime;

    
    public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public Byte getRepayType() {
        return repayType;
    }

    public void setRepayType(Byte repayType) {
        this.repayType = repayType;
    }

   

 

	
	public BigDecimal getRepayVal() {
		return repayVal;
	}

	public void setRepayVal(BigDecimal repayVal) {
		this.repayVal = repayVal;
	}

	public BigDecimal getRepayTotal() {
		return repayTotal;
	}

	public void setRepayTotal(BigDecimal repayTotal) {
		this.repayTotal = repayTotal;
	}



	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getBuyTotal() {
		return buyTotal;
	}

	public void setBuyTotal(BigDecimal buyTotal) {
		this.buyTotal = buyTotal;
	}

	public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
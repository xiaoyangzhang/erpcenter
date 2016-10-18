package com.yimayhd.erpcenter.dal.product.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZhengZiyu on 2015/7/23.
 */
public class PriceView implements Serializable{
    private static final long serialVersionUID = 2914802189321074524L;

    private Integer productId;

    private Integer groupId;

    private Integer priceId;
    
    @JSONField(format="yyyy-MM-dd")
    private Date groupDate;
    
    private Date groupDateTo;

    private Double priceSuggestAdult;

    private Double priceSuggestChild;
    
    private Double priceCostAdult;
    
    private Double priceCostChild;
    
    private Double priceSettlementAdult;
    
    private Double priceSettlementChild;

    private Integer stockCount;

    private Integer receiveCount;
    
    private Integer reserveCount;

    private Integer daysRegisterBegin;

    private Integer daysRegisterFinish;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public Date getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(Date groupDate) {
        this.groupDate = groupDate;
    }

    public Double getPriceSuggestAdult() {
        return priceSuggestAdult;
    }

    public void setPriceSuggestAdult(Double priceSuggestAdult) {
        this.priceSuggestAdult = priceSuggestAdult;
    }

    public Double getPriceSuggestChild() {
        return priceSuggestChild;
    }

    public void setPriceSuggestChild(Double priceSuggestChild) {
        this.priceSuggestChild = priceSuggestChild;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Integer getDaysRegisterBegin() {
        return daysRegisterBegin;
    }

    public void setDaysRegisterBegin(Integer daysRegisterBegin) {
        this.daysRegisterBegin = daysRegisterBegin;
    }

    public Integer getDaysRegisterFinish() {
        return daysRegisterFinish;
    }

    public void setDaysRegisterFinish(Integer daysRegisterFinish) {
        this.daysRegisterFinish = daysRegisterFinish;
    }

	public Double getPriceCostAdult() {
		return priceCostAdult;
	}

	public void setPriceCostAdult(Double priceCostAdult) {
		this.priceCostAdult = priceCostAdult;
	}

	public Double getPriceCostChild() {
		return priceCostChild;
	}

	public void setPriceCostChild(Double priceCostChild) {
		this.priceCostChild = priceCostChild;
	}

	public Double getPriceSettlementAdult() {
		return priceSettlementAdult;
	}

	public void setPriceSettlementAdult(Double priceSettlementAdult) {
		this.priceSettlementAdult = priceSettlementAdult;
	}

	public Double getPriceSettlementChild() {
		return priceSettlementChild;
	}

	public void setPriceSettlementChild(Double priceSettlementChild) {
		this.priceSettlementChild = priceSettlementChild;
	}

	public Integer getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(Integer reserveCount) {
		this.reserveCount = reserveCount;
	}

	public Date getGroupDateTo() {
		return groupDateTo;
	}

	public void setGroupDateTo(Date groupDateTo) {
		this.groupDateTo = groupDateTo;
	}    
    
}

package com.yimayhd.erpcenter.biz.product.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ProductGroupPrice implements Serializable {
    private static final long serialVersionUID = -2359227592953172075L;
    private Integer id;

    private Integer groupId;//组id

    @JSONField(format="yyyy-MM-dd")
    private Date groupDate;//团日期
    @JSONField(format="yyyy-MM-dd")
    private Date groupDateTo;//团日期
    private String groupDates;//前台接收

    private Integer weekDay;

    private Float priceSuggestAdult;//建议售价-成人

    private Float priceSuggestChild;//建议售价-儿童

    private Float priceSuggestRoomSpread;//建议售价-单房差

    private Float priceSettlementAdult;//结算价格-成人

    private Float priceSettlementChild;//结算价格-儿童

    private Float priceSettlementRoomeSpread;//结算价格-单房差
    
    private Float priceCostAdult;//成本价-成人

    private Float priceCostChild;//成本价-儿童

    private Float priceCostRoomSpread;//成本价-单房差
    private Integer daysRegisterBegin;//报名开始提前天数

    private Integer daysRegisterFinish;//报名结束提前天数

    private Integer stockCount;//库存数

    private Integer receiveCount;
    private Byte isMemeberAllocate;//是否名额分配

    
    private Long createTime;
    private Byte state;
    
    
    
    public Date getGroupDateTo() {
		return groupDateTo;
	}

	public void setGroupDateTo(Date groupDateTo) {
		this.groupDateTo = groupDateTo;
	}

	public String getGroupDates() {
		return groupDates;
	}

	public void setGroupDates(String groupDates) {
		this.groupDates = groupDates;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Float getPriceCostAdult() {
		return priceCostAdult;
	}

	public void setPriceCostAdult(Float priceCostAdult) {
		this.priceCostAdult = priceCostAdult == null ? 0f : priceCostAdult;
	}

	public Float getPriceCostChild() {
		return priceCostChild;
	}

	public void setPriceCostChild(Float priceCostChild) {
		this.priceCostChild = priceCostChild == null ? 0f : priceCostChild;
	}

	public Float getPriceCostRoomSpread() {
		return priceCostRoomSpread;
	}

	public void setPriceCostRoomSpread(Float priceCostRoomSpread) {
		this.priceCostRoomSpread = priceCostRoomSpread == null ? 0f : priceCostRoomSpread;
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

    public Date getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(Date groupDate) {
        this.groupDate = groupDate;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Float getPriceSuggestAdult() {
        return priceSuggestAdult;
    }

    public void setPriceSuggestAdult(Float priceSuggestAdult) {
        this.priceSuggestAdult = priceSuggestAdult == null ?  0f : priceSuggestAdult;
    }

    public Float getPriceSuggestChild() {
        return priceSuggestChild;
    }

    public void setPriceSuggestChild(Float priceSuggestChild) {
        this.priceSuggestChild = priceSuggestChild == null ?  0f : priceSuggestChild;
    }

    public Float getPriceSuggestRoomSpread() {
        return priceSuggestRoomSpread;
    }

    public void setPriceSuggestRoomSpread(Float priceSuggestRoomSpread) {
        this.priceSuggestRoomSpread = priceSuggestRoomSpread == null ?  0f : priceSuggestRoomSpread;
    }

    public Float getPriceSettlementAdult() {
        return priceSettlementAdult;
    }

    public void setPriceSettlementAdult(Float priceSettlementAdult) {
        this.priceSettlementAdult = priceSettlementAdult == null ? 0f : priceSettlementAdult;
    }

    public Float getPriceSettlementChild() {
        return priceSettlementChild;
    }

    public void setPriceSettlementChild(Float priceSettlementChild) {
        this.priceSettlementChild = priceSettlementChild == null ? 0f : priceSettlementChild;
    }

    public Float getPriceSettlementRoomeSpread() {
        return priceSettlementRoomeSpread;
    }

    public void setPriceSettlementRoomeSpread(Float priceSettlementRoomeSpread) {
        this.priceSettlementRoomeSpread = priceSettlementRoomeSpread == null ? 0f : priceSettlementRoomeSpread;
    }

    public Integer getDaysRegisterBegin() {
        return daysRegisterBegin;
    }

    public void setDaysRegisterBegin(Integer daysRegisterBegin) {
        this.daysRegisterBegin = daysRegisterBegin == null ? 0 : daysRegisterBegin;
    }

    public Integer getDaysRegisterFinish() {
        return daysRegisterFinish;
    }

    public void setDaysRegisterFinish(Integer daysRegisterFinish) {
        this.daysRegisterFinish = daysRegisterFinish == null ? 0 : daysRegisterFinish;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount == null ? 0 : stockCount;
    }

   

    public Byte getIsMemeberAllocate() {
		return isMemeberAllocate;
	}

	public void setIsMemeberAllocate(Byte isMemeberAllocate) {
		this.isMemeberAllocate = isMemeberAllocate;
	}

	public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount == null ? 0 : receiveCount;
    }
}
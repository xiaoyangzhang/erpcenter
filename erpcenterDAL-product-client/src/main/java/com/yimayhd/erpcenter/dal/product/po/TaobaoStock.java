package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class TaobaoStock implements Serializable {
    
    private static final long serialVersionUID = -1308913655008578675L;

    private Integer id;
    private String stockName;
    private Integer clearDayReset;
    private String brief;
    
    private Integer sumProductId;
    
    public Integer getSumProductId() {
		return sumProductId;
	}

	public void setSumProductId(Integer sumProductId) {
		this.sumProductId = sumProductId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public Integer getClearDayReset() {
        return clearDayReset;
    }

    public void setClearDayReset(Integer clearDayReset) {
        this.clearDayReset = clearDayReset;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }
}
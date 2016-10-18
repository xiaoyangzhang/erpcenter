package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ProductStock implements Serializable {
    private Integer id;

    private Integer productId;
    
    @JSONField(format="yyyy-MM-dd")
    private Date itemDate;

    private Integer stockCount;

    private Integer receiveCount;
    
    private Integer reserveCount;
    
    private Integer state;

    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(Integer reserveCount) {
		this.reserveCount = reserveCount;
	}
	
    
}
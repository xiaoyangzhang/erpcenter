package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class TaobaoStockLog  implements Serializable {
    
    private static final long serialVersionUID = -4894035119521328375L;

    private Integer id;
    private Integer stockId;
    private Integer stockDateId;
    private Integer orderId;
    private Integer num;
    private String createUser;
    private String createTime;
    private Integer taobaoOrderId;
    
    
    public Integer getTaobaoOrderId() {
		return taobaoOrderId;
	}

	public void setTaobaoOrderId(Integer taobaoOrderId) {
		this.taobaoOrderId = taobaoOrderId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getStockDateId() {
        return stockDateId;
    }

    public void setStockDateId(Integer stockDateId) {
        this.stockDateId = stockDateId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
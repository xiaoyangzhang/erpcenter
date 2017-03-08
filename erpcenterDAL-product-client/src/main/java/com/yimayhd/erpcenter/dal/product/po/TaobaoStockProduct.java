package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;

public class TaobaoStockProduct  implements Serializable {
   
    private static final long serialVersionUID = 6292087171711111274L;

    private Integer id;
    private Integer productId;
    private Integer stockId;
    private Integer productSkusId;
    
    
    public Integer getProductSkusId() {
		return productSkusId;
	}

	public void setProductSkusId(Integer productSkusId) {
		this.productSkusId = productSkusId;
	}

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

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
}
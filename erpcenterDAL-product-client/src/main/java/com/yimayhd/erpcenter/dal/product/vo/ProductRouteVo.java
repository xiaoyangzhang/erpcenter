package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductRemark;


/**
 * Created by ZhengZiyu on 2015/7/10.
 */
public class ProductRouteVo implements Serializable{
    private static final long serialVersionUID = -7829700731114911790L;

    private Integer productId;

    private List<ProductRouteDayVo> productRoteDayVoList;
    private ProductRemark productRemark;
    

    public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}

	public List<ProductRouteDayVo> getProductRoteDayVoList() {
        return productRoteDayVoList;
    }

    public void setProductRoteDayVoList(List<ProductRouteDayVo> productRoteDayVoList) {
        this.productRoteDayVoList = productRoteDayVoList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}

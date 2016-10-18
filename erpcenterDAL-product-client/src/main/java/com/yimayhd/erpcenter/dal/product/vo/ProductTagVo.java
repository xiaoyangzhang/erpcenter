package com.yimayhd.erpcenter.dal.product.vo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductTag;

/**
 * Created by ZhengZiyu on 2015/7/3.
 */
public class ProductTagVo implements Serializable{
    private static final long serialVersionUID = 2157186698714698553L;
    private List<ProductTag> productTags = new ArrayList<ProductTag>();

    private Integer productId;

    public List<ProductTag> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<ProductTag> productTags) {
        this.productTags = productTags;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}

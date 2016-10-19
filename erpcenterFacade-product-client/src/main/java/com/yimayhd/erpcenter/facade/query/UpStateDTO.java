package com.yimayhd.erpcenter.facade.query;

import com.yimayhd.erpcenter.dal.product.po.ProductInfo;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 18:08
 */
public class UpStateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private ProductInfo info;

    public ProductInfo getInfo() {
        return info;
    }

    public void setInfo(ProductInfo info) {
        this.info = info;
    }
}

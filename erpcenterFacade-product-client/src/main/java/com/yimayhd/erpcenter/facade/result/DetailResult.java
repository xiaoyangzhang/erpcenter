package com.yimayhd.erpcenter.facade.result;

import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:00
 */
public class DetailResult implements Serializable{
    private static final long serialVersionUID = -2235152751651905167L;
    private ProductInfoVo productInfoVo;
    private ProductRouteVo productRouteVo;
    private ProductRemark productRemark;
    private List<ProductGroup> productGroups;

    public ProductInfoVo getProductInfoVo() {
        return productInfoVo;
    }

    public void setProductInfoVo(ProductInfoVo productInfoVo) {
        this.productInfoVo = productInfoVo;
    }

    public ProductRouteVo getProductRouteVo() {
        return productRouteVo;
    }

    public void setProductRouteVo(ProductRouteVo productRouteVo) {
        this.productRouteVo = productRouteVo;
    }

    public ProductRemark getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(ProductRemark productRemark) {
        this.productRemark = productRemark;
    }

    public List<ProductGroup> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<ProductGroup> productGroups) {
        this.productGroups = productGroups;
    }
}

package com.yimayhd.erpcenter.dal.product.vo;


import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;

/**
 * Created by ZhengZiyu on 2015/7/10.
 */
public class ProductRouteDayVo implements Serializable{

    private static final long serialVersionUID = 8837895045063405469L;
    private List<ProductRouteTraffic> productRouteTrafficList;

    private ProductRoute productRoute;

    private List<ProductRouteSupplier> productOptionsSupplierList;

    private List<ProductAttachment> productAttachments;

    public List<ProductRouteTraffic> getProductRouteTrafficList() {
        return productRouteTrafficList;
    }

    public void setProductRouteTrafficList(List<ProductRouteTraffic> productRouteTrafficList) {
        this.productRouteTrafficList = productRouteTrafficList;
    }

    public ProductRoute getProductRoute() {
        return productRoute;
    }

    public void setProductRoute(ProductRoute productRoute) {
        this.productRoute = productRoute;
    }


    public List<ProductRouteSupplier> getProductOptionsSupplierList() {
        return productOptionsSupplierList;
    }

    public void setProductOptionsSupplierList(List<ProductRouteSupplier> productOptionsSupplierList) {
        this.productOptionsSupplierList = productOptionsSupplierList;
    }

    public List<ProductAttachment> getProductAttachments() {
        return productAttachments;
    }

    public void setProductAttachments(List<ProductAttachment> productAttachments) {
        this.productAttachments = productAttachments;
    }
}

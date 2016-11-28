package com.yimayhd.erpcenter.facade.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;

import java.util.List;

/**
 * Created by liyong on 2016/11/28.
 */
public class ToCopyResult extends ResultSupport{
    private ProductInfoVo productInfoVo;
    private ProductRemark productRemark;
    // 产品名称
   private  List<DicInfo> brandList;

    public ProductInfoVo getProductInfoVo() {
        return productInfoVo;
    }

    public void setProductInfoVo(ProductInfoVo productInfoVo) {
        this.productInfoVo = productInfoVo;
    }

    public ProductRemark getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(ProductRemark productRemark) {
        this.productRemark = productRemark;
    }

    public List<DicInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<DicInfo> brandList) {
        this.brandList = brandList;
    }
}

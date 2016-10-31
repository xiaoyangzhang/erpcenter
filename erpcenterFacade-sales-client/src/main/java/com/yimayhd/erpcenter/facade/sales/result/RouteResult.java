package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:35
 */
public class RouteResult extends ResultSupport {
    private  List<DicInfo> dicInfoList;
    private PageBean page;
    private ProductRouteVo productRouteVo;

    public ProductRouteVo getProductRouteVo() {
        return productRouteVo;
    }

    public void setProductRouteVo(ProductRouteVo productRouteVo) {
        this.productRouteVo = productRouteVo;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DicInfo> getDicInfoList() {
        return dicInfoList;
    }

    public void setDicInfoList(List<DicInfo> dicInfoList) {
        this.dicInfoList = dicInfoList;
    }
}

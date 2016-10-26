package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 13:32
 */
public class ToSearchListResult extends ResultSupport {
    private List<DicInfo> brandList;
    private PageBean pageBean;

    public List<DicInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<DicInfo> brandList) {
        this.brandList = brandList;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}

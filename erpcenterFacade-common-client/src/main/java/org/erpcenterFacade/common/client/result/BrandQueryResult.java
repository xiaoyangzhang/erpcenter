package org.erpcenterFacade.common.client.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/19 11:20
 */
public class BrandQueryResult extends ResultSupport{
    private List<DicInfo> brandList;//产品品牌

    public List<DicInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<DicInfo> brandList) {
        this.brandList = brandList;
    }
}

package com.yimayhd.erpcenter.facade.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 20:19
 */
public class ToSearchListStateResult extends ResultSupport{
    private List<RegionInfo> allProvince;
    private PageBean pageBean;
    private List<DicInfo> brandList;
    private Map<Integer, String> priceStateMap;

    public Map<Integer, String> getPriceStateMap() {
        return priceStateMap;
    }

    public void setPriceStateMap(Map<Integer, String> priceStateMap) {
        this.priceStateMap = priceStateMap;
    }

    public List<RegionInfo> getAllProvince() {
        return allProvince;
    }

    public void setAllProvince(List<RegionInfo> allProvince) {
        this.allProvince = allProvince;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public List<DicInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<DicInfo> brandList) {
        this.brandList = brandList;
    }
}

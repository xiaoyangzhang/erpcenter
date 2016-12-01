package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/1 15:23
 */
public class PlaceOrderQueryResult extends BaseResult {
    private List<RegionInfo> allProvince;
    private List<DicInfo> cashTypes;
    private PageBean pageBean;
    private Map sum;

    public List<RegionInfo> getAllProvince() {
        return allProvince;
    }

    public void setAllProvince(List<RegionInfo> allProvince) {
        this.allProvince = allProvince;
    }

    public List<DicInfo> getCashTypes() {
        return cashTypes;
    }

    public void setCashTypes(List<DicInfo> cashTypes) {
        this.cashTypes = cashTypes;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map getSum() {
        return sum;
    }

    public void setSum(Map sum) {
        this.sum = sum;
    }
}

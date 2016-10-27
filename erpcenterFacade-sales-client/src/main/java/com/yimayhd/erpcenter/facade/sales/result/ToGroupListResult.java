package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 11:22
 */
public class ToGroupListResult extends ResultSupport {
    private List<RegionInfo> allProvince;
    private String orgJsonStr;
    private String orgUserJsonStr;
    private List<DicInfo> sourceTypeList;

    public List<RegionInfo> getAllProvince() {
        return allProvince;
    }

    public void setAllProvince(List<RegionInfo> allProvince) {
        this.allProvince = allProvince;
    }

    public String getOrgJsonStr() {
        return orgJsonStr;
    }

    public void setOrgJsonStr(String orgJsonStr) {
        this.orgJsonStr = orgJsonStr;
    }

    public String getOrgUserJsonStr() {
        return orgUserJsonStr;
    }

    public void setOrgUserJsonStr(String orgUserJsonStr) {
        this.orgUserJsonStr = orgUserJsonStr;
    }

    public List<DicInfo> getSourceTypeList() {
        return sourceTypeList;
    }

    public void setSourceTypeList(List<DicInfo> sourceTypeList) {
        this.sourceTypeList = sourceTypeList;
    }

}

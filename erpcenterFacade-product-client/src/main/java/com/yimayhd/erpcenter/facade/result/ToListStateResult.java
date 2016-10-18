package com.yimayhd.erpcenter.facade.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class ToListStateResult extends ResultSupport{
    private static final long serialVersionUID = -1L;
    private String orgJsonStr;
    private String orgUserJsonStr;
    private  List<DicInfo> brandList;

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

    public List<DicInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<DicInfo> brandList) {
        this.brandList = brandList;
    }
}

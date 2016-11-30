package com.yimayhd.erpcenter.facade.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyong on 2016/11/28.
 */
public class TaoBaoProductListResult  extends ResultSupport {

   private  List<DicInfo> brandList = new ArrayList<DicInfo>();

    private String OrgTreeJsonStr;

    private String OrgUserTreeJsonStr;

    // 省市
    private List<RegionInfo> allProvince = new ArrayList<RegionInfo>();

    private PageBean pageBean = new PageBean();

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

    public String getOrgTreeJsonStr() {
        return OrgTreeJsonStr;
    }

    public void setOrgTreeJsonStr(String orgTreeJsonStr) {
        OrgTreeJsonStr = orgTreeJsonStr;
    }

    public String getOrgUserTreeJsonStr() {
        return OrgUserTreeJsonStr;
    }

    public void setOrgUserTreeJsonStr(String orgUserTreeJsonStr) {
        OrgUserTreeJsonStr = orgUserTreeJsonStr;
    }
}

package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpresource.dal.po.SupplierContactMan;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 11:50
 */
public class ContactManListResult extends ResultSupport  {
    public List<SupplierContactMan> getManList() {
        return manList;
    }

    public void setManList(List<SupplierContactMan> manList) {
        this.manList = manList;
    }

    private List<SupplierContactMan> manList;


}

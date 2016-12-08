package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
public class PlatformOrgSupplierAuthResult extends ResultSupport implements Serializable {
    private List<Integer> supplierIds;
    private List<Integer> orgOwnSupplierIds;
    public PlatformOrgSupplierAuthResult(List<Integer> supplierIds,List<Integer> orgOwnSupplierIds){
        this.supplierIds = supplierIds;
        this.orgOwnSupplierIds = orgOwnSupplierIds;
    }
    public List<Integer> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<Integer> supplierIds) {
        this.supplierIds = supplierIds;
    }

    public List<Integer> getOrgOwnSupplierIds() {
        return orgOwnSupplierIds;
    }

    public void setOrgOwnSupplierIds(List<Integer> orgOwnSupplierIds) {
        this.orgOwnSupplierIds = orgOwnSupplierIds;
    }
}

package com.yimayhd.erpcenter.biz.sys.service.impl;

import com.yimayhd.erpcenter.biz.sys.service.SysDataRightSupplierBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;
import com.yimayhd.erpcenter.dal.sys.service.SysDataRightSupplierDal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public class SysDataRightSupplierBizImpl implements SysDataRightSupplierBiz {
    @Autowired
    SysDataRightSupplierDal sysDataRightSupplierDal;
    @Override
    public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId) {
        return sysDataRightSupplierDal.selectSysDataRightSupplierByOrgId(orgId);
    }

    @Override
    public List<Integer> selectOrgAuthSupplierIds(Map<String, Object> params) {
        return sysDataRightSupplierDal.selectOrgAuthSupplierIds(params);
    }

    @Override
    public void saveOrgAuthSuppliers(int orgId, int bizId, List<Integer> supplierIds) {
        sysDataRightSupplierDal.saveOrgAuthSuppliers(orgId,bizId,supplierIds);
    }

    @Override
    public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(String orgIds) {
        return sysDataRightSupplierDal.selectSysDataRightSupplierInOrgIds(orgIds);
    }
}

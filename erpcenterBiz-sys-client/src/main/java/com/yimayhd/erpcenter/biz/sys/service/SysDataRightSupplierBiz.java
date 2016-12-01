package com.yimayhd.erpcenter.biz.sys.service;

import com.yimayhd.erpcenter.dal.sys.po.LoginLogPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;

import java.util.List;
import java.util.Map;

public interface SysDataRightSupplierBiz {
	public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId);

	public List<Integer> selectOrgAuthSupplierIds(Map<String,Object> params);

	public void saveOrgAuthSuppliers(int orgId,int bizId,List<Integer> supplierIds);

	public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(String orgIds);
}

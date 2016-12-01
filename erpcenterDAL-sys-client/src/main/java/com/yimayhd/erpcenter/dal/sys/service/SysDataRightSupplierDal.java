package com.yimayhd.erpcenter.dal.sys.service;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;

public interface SysDataRightSupplierDal {

	public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId);
	
	public List<Integer> selectOrgAuthSupplierIds(Map<String,Object> params);
	
	public void saveOrgAuthSuppliers(int orgId,int bizId,List<Integer> supplierIds);
	
	public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(String orgIds);
}

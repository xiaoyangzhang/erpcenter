package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;

public interface SysDataRightSupplierMapper {

	int insert(SysDataRightSupplier sysDataRightSupplier);

	SysDataRightSupplier select(int id);

	void update(SysDataRightSupplier sysDataRightSupplier);

	void delete(int id);
	
	List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId);
	
	/**
	 * 查询机构是否有对应旅行社权限
	 * @param params {orgId:"xxx",supplierIds:"xxx"}
	 * @return
	 */
	List<Integer> selectOrgAuthSupplierIds(Map<String,Object> params);
	
	List<SysDataRightSupplier> selectSupplierIdInOrgIds(String orgIds);
}

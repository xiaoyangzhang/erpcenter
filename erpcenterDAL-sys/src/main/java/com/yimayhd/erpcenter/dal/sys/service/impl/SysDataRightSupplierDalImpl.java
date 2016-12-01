package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sys.dao.SysDataRightSupplierMapper;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;
import com.yimayhd.erpcenter.dal.sys.service.SysDataRightSupplierDal;

public class SysDataRightSupplierDalImpl implements SysDataRightSupplierDal {
	
	@Autowired
	private SysDataRightSupplierMapper sysDataRightSupplierMapper;
	
	@Override
	public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId){
		List<SysDataRightSupplier> lists=sysDataRightSupplierMapper.selectSysDataRightSupplierByOrgId(orgId);
		return lists;
	}
	
	@Override
	public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(String orgIds){
		List<SysDataRightSupplier> lists=sysDataRightSupplierMapper.selectSupplierIdInOrgIds(orgIds);
		return lists;
	}
	
	/**
	 * 查询机构有 supplierIds里的授权
	 */
	public List<Integer> selectOrgAuthSupplierIds(Map<String,Object> params){
		return sysDataRightSupplierMapper.selectOrgAuthSupplierIds(params);
	}

	/**
	 * 保存机构旅行社授权
	 */
	@Override
	public void saveOrgAuthSuppliers(int orgId, int bizId, List<Integer> supplierIds) {
		if(null != supplierIds && supplierIds.size() > 0 && orgId > 0 )
		{
			for(Integer supplierId:supplierIds)
			{
				SysDataRightSupplier sysDataRightSupplier = new SysDataRightSupplier();
				sysDataRightSupplier.setBizId(bizId);
				sysDataRightSupplier.setOrgId(orgId);
				sysDataRightSupplier.setSupplierId(supplierId);
				sysDataRightSupplierMapper.insert(sysDataRightSupplier);
			}
		}
	}
	
	
}


package com.yimayhd.erpcenter.biz.sys.service.impl;

import com.yihg.mybatis.utility.PageBean;
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
	public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(
			Integer orgId) {
		// TODO Auto-generated method stub
		return sysDataRightSupplierDal.selectSysDataRightSupplierByOrgId(orgId);
	}

	@Override
	public void saveOrgAuthSuppliers(int orgId, int bizId, List supplierIds,
			List delSupplierIds) {
		// TODO Auto-generated method stub
		sysDataRightSupplierDal.saveOrgAuthSuppliers(orgId, bizId, supplierIds, delSupplierIds);
	}

	@Override
	public PageBean selectSupplierIdsListPage(PageBean page, int orgId,
			int bizId) {
		// TODO Auto-generated method stub
		return sysDataRightSupplierDal.selectSupplierIdsListPage(page, orgId, bizId);
	}

	@Override
	public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(
			Integer orgId) {
		// TODO Auto-generated method stub
		return sysDataRightSupplierDal.selectSysDataRightSupplierInOrgIds(orgId);
	}

	@Override
	public List<Integer> selectAllOrgAuthSupplierIds(int orgId, int bizId) {
		// TODO Auto-generated method stub
		return sysDataRightSupplierDal.selectAllOrgAuthSupplierIds(orgId, bizId);
	}

	@Override
	public List<Integer> selectOwnOrgSupplierIds(int orgid, int bizid) {
		// TODO Auto-generated method stub
		return sysDataRightSupplierDal.selectOwnOrgSupplierIds(orgid, bizid);
	}

}

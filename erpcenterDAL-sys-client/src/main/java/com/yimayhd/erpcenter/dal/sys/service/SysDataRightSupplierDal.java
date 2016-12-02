package com.yimayhd.erpcenter.dal.sys.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;

public interface SysDataRightSupplierDal {

	public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId);
	
	public void saveOrgAuthSuppliers(int orgId,int bizId,List supplierIds,List delSupplierIds);
	
	/**
	 * 根据机构id查询已经拥有的组团社权限 代分页
	 * @author daixiaoman
	 */
	public PageBean selectSupplierIdsListPage(PageBean page,int orgId,int bizId);
	
	public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(Integer orgId);
	
	/**
	 * 获取组织机构ids下的所有组团社权限 不带分页
	 * @author daixiaoman
	 */
	public List<Integer> selectAllOrgAuthSupplierIds(int orgId,int bizId);
	
	/**
	 * 查询机构特有的组团社权限
	 * @author daixiaoman
	 * @date 2016年11月30日 下午4:45:46
	 */
	public List<Integer> selectOwnOrgSupplierIds(int orgid,int bizid);
	
}

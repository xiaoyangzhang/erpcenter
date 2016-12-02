package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;

public interface SysDataRightSupplierMapper {

	int insert(SysDataRightSupplier sysDataRightSupplier);

	SysDataRightSupplier select(int id);

	void update(SysDataRightSupplier sysDataRightSupplier);

	void delete(int id);

	List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId);

	/**
	 * @author daixiaoman 根据机构ids 获取已经有的旅行社的权限,带分页
	 */
	List<Integer> selectSupplierIdsListPage(@Param("page") PageBean page);

	List<SysDataRightSupplier> selectSupplierIdInOrgIds(Map<String,Object> params);

	/**
	 * @author daixiaoman 获取机构ids的所有的组团社权限,不带分页
	 */
	List<Integer> selectAllOrgAuthSupplierIds(Map<String, Object> params);

	/**
	 * 查询机构特有的组团社权限
	 * 
	 * @author daixiaoman
	 * @date 2016年11月30日 下午4:45:46
	 */
	public List<Integer> selectOwnOrgSupplierIds(Map<String, Object> params);

	/**
	 * 删除组织机构特有组团社权限
	 * 
	 * @author daixiaoman
	 * @date 2016年11月30日 下午4:46:50
	 */
	public void deleteOrgSupplierAuths(Map<String, Object> params);

}

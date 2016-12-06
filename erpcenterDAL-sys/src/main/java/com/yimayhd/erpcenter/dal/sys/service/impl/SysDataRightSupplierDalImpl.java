package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.dao.PlatformOrgMapper;
import com.yimayhd.erpcenter.dal.sys.dao.SysDataRightSupplierMapper;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;
import com.yimayhd.erpcenter.dal.sys.service.SysDataRightSupplierDal;
import com.yimayhd.erpcenter.dal.sys.utils.StrUtils;

public class SysDataRightSupplierDalImpl implements SysDataRightSupplierDal{

	@Autowired
	private SysDataRightSupplierMapper sysDataRightSupplierMapper;

	@Autowired
	private PlatformOrgMapper platformOrgMapper;

	@Override
	public List<SysDataRightSupplier> selectSysDataRightSupplierByOrgId(Integer orgId) {
		List<SysDataRightSupplier> lists = sysDataRightSupplierMapper.selectSysDataRightSupplierByOrgId(orgId);
		return lists;
	}

	@Override
	public List<SysDataRightSupplier> selectSysDataRightSupplierInOrgIds(Integer orgId) {
		List<String> orgids = getOrgidsByPath(orgId);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgids", orgids);
		List<SysDataRightSupplier> lists = sysDataRightSupplierMapper.selectSupplierIdInOrgIds(params);
		return lists;
	}

	/**
	 * @author daixiaoman 保存机构旅行社授权
	 */
	@Override
	public void saveOrgAuthSuppliers(int orgId, int bizId, List supplierIds,List delSupplierIds) {
		//新增的组团社权限
		if (null != supplierIds && supplierIds.size() > 0 && orgId > 0) {
			for (Object supplierId : supplierIds) {
				SysDataRightSupplier sysDataRightSupplier = new SysDataRightSupplier();
				sysDataRightSupplier.setBizId(bizId);
				sysDataRightSupplier.setOrgId(orgId);
				sysDataRightSupplier.setSupplierId(Integer.parseInt(supplierId.toString()));
				sysDataRightSupplierMapper.insert(sysDataRightSupplier);
			}
		}
		
		//要被删除的组团社权限
		if(null != delSupplierIds && delSupplierIds.size() > 0){
			for(Object delSupplierId:delSupplierIds){
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("orgid",orgId);
				params.put("bizId", bizId);
				params.put("supplierId",Integer.parseInt(delSupplierId.toString()));
				sysDataRightSupplierMapper.deleteOrgSupplierAuths(params);
			}
		}
	}

	/**
	 * 获取组织结构路径的组织id
	 * @author daixiaoman
	 * @date 2016年11月29日 下午5:13:02
	 */
	private List<String> getOrgidsByPath(int orgId) {
		PlatformOrgPo org = platformOrgMapper.findByOrgId(orgId);
		if (null != org) {
			String orgPath = !"".equals(org.getOrgPath()) ? org.getOrgPath() : orgId + "";
			List<String> orgids = StrUtils.strSplitList(StrUtils.trimLastAndFirstStr(orgPath, "-"), "-");
			return orgids;
		}
		return new ArrayList<String>();
	}

	/**
	 * 获取机构的已有的组团社权限 带分页
	 * @author daixiaoman
	 * @date 2016年11月29日 下午5:12:52
	 */
	@Override
	public PageBean selectSupplierIdsListPage( PageBean page, int orgId, int bizId) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> orgids = getOrgidsByPath(orgId);
		// 防止为空时查询所有
		orgids.add("-1");
		params.put("orgids", orgids);
		params.put("bizId", bizId);
		page.setParameter(params);
		List<Integer> supplierids = sysDataRightSupplierMapper.selectSupplierIdsListPage(page);
		page.setResult(supplierids);
		return page;
	}

	/**
	 * 获取组织机构下的组团社分页 不带分页
	 * @author daixiaoman
	 * @date 2016年11月29日 下午5:12:38
	 */
	@Override
	public List<Integer> selectAllOrgAuthSupplierIds(int orgId, int bizId) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> orgids = getOrgidsByPath(orgId);
		// 防止为空时查询所有
		orgids.add("-1");
		params.put("orgids", orgids);
		params.put("bizId", bizId);
		return sysDataRightSupplierMapper.selectAllOrgAuthSupplierIds(params);
	}

	/**
	 * 查询机构特有的组团社权限
	 * @author daixiaoman
	 * @date 2016年11月30日 下午4:45:46
	 */
	@Override
	public List<Integer> selectOwnOrgSupplierIds(int orgid,int bizId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bizId",bizId);
		params.put("orgid",orgid);
		return sysDataRightSupplierMapper.selectOwnOrgSupplierIds(params);
	}

}


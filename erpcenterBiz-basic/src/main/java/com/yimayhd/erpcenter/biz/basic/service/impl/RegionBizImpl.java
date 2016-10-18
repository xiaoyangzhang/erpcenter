package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.basic.service.RegionDal;

public class RegionBizImpl implements RegionDal {
	@Autowired
	private RegionDal regionDal;

	@Override
	public List<RegionInfo> getRegionById(String pid) {
		return regionDal.getRegionById(pid);
	}

	@Override
	public List<RegionInfo> getAllProvince() {
		return regionDal.getAllProvince();
	}

	@Override
	public RegionInfo getById(String id) {
		return regionDal.getById(id);
	}

	@Override
	public int update(RegionInfo dicRegion) {
		return regionDal.update(dicRegion);
	}

	@Override
	public int isNode(String id) {
		return regionDal.isNode(id);
	}

	@Override
	public int delNode(String id) {
		return regionDal.delNode(id);
	}

	@Override
	public int add(RegionInfo dicRegion) {
		return regionDal.add(dicRegion);
	}

	@Override
	public void uploadRegion() {
		regionDal.uploadRegion();
	}
}

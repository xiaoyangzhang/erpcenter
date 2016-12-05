package com.yimayhd.erpcenter.facade.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.basic.errorcode.BasicErrorCode;
import com.yimayhd.erpcenter.facade.basic.result.ResultSupport;
import com.yimayhd.erpcenter.facade.basic.service.RegionFacade;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public class RegionFacadeImpl implements RegionFacade {

	@Autowired
	private RegionBiz regionBiz;
	
	@Override
	public List<RegionInfo> getRegionById(String pid) {
		
		List<RegionInfo> list = regionBiz.getRegionById(pid);
		return list;
	}

	@Override
	public RegionInfo getById(String id) {
		RegionInfo dicRegion = regionBiz.getById(id);
		return dicRegion;
	}

	@Override
	public int update(RegionInfo dicRegion) {
		int i = regionBiz.update(dicRegion);
		return i;
	}

	@Override
	public int isNode(String id) {
		int i =  regionBiz.isNode(id);
		return i;
	}

	@Override
	public int add(RegionInfo dicRegion) {
		int i =  regionBiz.add(dicRegion);
		return i;
	}

	@Override
	public List<RegionInfo> getAllProvince() {
		return regionBiz.getAllProvince();
	}

}

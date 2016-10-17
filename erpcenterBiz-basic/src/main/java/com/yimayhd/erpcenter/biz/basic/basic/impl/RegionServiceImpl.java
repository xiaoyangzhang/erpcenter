package com.yimayhd.erpcenter.biz.basic.basic.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.architect.redis.JedisManager;
import com.yimayhd.erpcenter.biz.basic.client.basic.api.RegionService;
import com.yimayhd.erpcenter.biz.basic.client.basic.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.client.basic.dao.RegionMapper;
import com.yimayhd.erpcenter.dal.basic.client.basic.po.RegionInfo;

public class RegionServiceImpl implements RegionService {
	static final Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);
	@Autowired
	private RegionMapper regionMapper;

	@Override
	public List<RegionInfo> getRegionById(String pid) {
		if(StringUtils.isNumeric(pid)){
		List<RegionInfo> regionList = null;
				Object obj = JedisManager.getObject(BasicConstants.REGION_KEY_PREFIX+pid);
		if (obj!=null) {
			logger.info("读取redis");
			regionList=(List<RegionInfo>)obj;
			return regionList;
		}
		else {
			Integer parent = Integer.valueOf(pid);
			return regionMapper.getRegionById(parent);			
		}
		}
		return null;
	}

	@Override
	public List<RegionInfo> getAllProvince() {
		List<RegionInfo> provinces=null;
		Object obj = JedisManager.getObject(BasicConstants.REGION_KEY_PREFIX+0);
		if (obj!=null) {
			logger.info("读取redis");
			provinces=(List<RegionInfo>)obj;
			return provinces;
		}else {
			
			return regionMapper.getAllProvince();
		}
	}

	@Override
	public RegionInfo getById(String id) {
		if(StringUtils.isNumeric(id)){
			Integer regionId = Integer.valueOf(id);
			return regionMapper.getById(regionId);
		}
		return null;
	}

	@Override
	public int update(RegionInfo dicRegion) {
		return regionMapper.update(dicRegion);
	}

	@Override
	public int isNode(String id) {
		if(StringUtils.isNumeric(id)){
			Integer regionId = Integer.valueOf(id);
			return regionMapper.isNode(regionId);
		}
		return  0;
	}

	@Override
	public int delNode(String id) {
		if(StringUtils.isNumeric(id)){
			Integer regionId = Integer.valueOf(id);
			return regionMapper.delNode(regionId);
		}
		return -1;
		
	}

	@Override
	public int add(RegionInfo regionInfo) {
		int curLevel = regionInfo.getLevel()+1;
		regionInfo.setLevel(curLevel);
		regionInfo.setStatus(1);
		int maxId = regionMapper.getMaxIdByLevel(curLevel);
		regionInfo.setId(maxId+1);		
		return regionMapper.add(regionInfo);
	}

	@Override
	public void uploadRegion() {
		List<RegionInfo> allProvinces = getAllProvince();
		JedisManager.setObject(BasicConstants.REGION_KEY_PREFIX+0,0, allProvinces);
		for (RegionInfo province : allProvinces) {
			List<RegionInfo> cities = getRegionById(String.valueOf(province.getId()));
			JedisManager.setObject(BasicConstants.REGION_KEY_PREFIX+province.getId(), 0, cities);
			for (RegionInfo city : cities) {
				List<RegionInfo> districts = getRegionById(String.valueOf(city.getId()));
				JedisManager.setObject(BasicConstants.REGION_KEY_PREFIX+city.getId(), 0, districts);
			}
		}
	}

}

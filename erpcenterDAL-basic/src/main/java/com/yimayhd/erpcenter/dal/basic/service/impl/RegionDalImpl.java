package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.base.cache.core.factory.ManagerFactory;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.dao.RegionMapper;
import com.yimayhd.erpcenter.dal.basic.dto.ReginCacheDTO;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.basic.service.RegionDal;

public class RegionDalImpl implements RegionDal {
	static final Logger logger = LoggerFactory.getLogger(RegionDalImpl.class);
	@Autowired
	private RegionMapper regionMapper;
	@Autowired
	private ManagerFactory managerFactory;

	@Override
	public List<RegionInfo> getRegionById(String pid) {
		if(StringUtils.isNumeric(pid)){
		List<RegionInfo> regionList = null;
		
		String key = BasicConstants.REGION_KEY_PREFIX+pid;
		String valueJson = managerFactory.getStringCommands().get(key.hashCode(), key);
		if (!StringUtils.isEmpty(valueJson)) {
			logger.info("读取redis");
			ReginCacheDTO regionDTO  = JSONObject.parseObject(valueJson,ReginCacheDTO.class);
			regionList= regionDTO.getRegionList();
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
		//List<RegionInfo> provinces=null;
		String key = BasicConstants.REGION_KEY_PREFIX+0;
		String valueJson = managerFactory.getStringCommands().get(key.hashCode(), key);
		
		if(!StringUtils.isEmpty(valueJson)){
			ReginCacheDTO regionDTO  = JSONObject.parseObject(valueJson,ReginCacheDTO.class);
			return  regionDTO.getRegionList();
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
		String key = BasicConstants.REGION_KEY_PREFIX+0;
		
		ReginCacheDTO regionDTO = new ReginCacheDTO();
		regionDTO.setRegionList(allProvinces);
		String valueJson = JSONObject.toJSONString(regionDTO);
		
		managerFactory.getStringCommands().set(key.hashCode(), key, valueJson);
		
		for (RegionInfo province : allProvinces) {
			List<RegionInfo> cities = getRegionById(String.valueOf(province.getId()));
			
			String cityListKey = BasicConstants.REGION_KEY_PREFIX+province.getId();
			ReginCacheDTO regionCityListDTO = new ReginCacheDTO();
			regionCityListDTO.setRegionList(cities);
			
			managerFactory.getStringCommands().set(cityListKey.hashCode(), cityListKey, JSONObject.toJSONString(regionDTO));
			
			for (RegionInfo city : cities) {
				List<RegionInfo> districts = getRegionById(String.valueOf(city.getId()));
				
				String districtKey = BasicConstants.REGION_KEY_PREFIX+city.getId();
				ReginCacheDTO regionDisListDTO = new ReginCacheDTO();
				regionDisListDTO.setRegionList(districts);
				
				managerFactory.getStringCommands().set(districtKey.hashCode(), districtKey, JSONObject.toJSONString(regionDisListDTO));
			}
		}
	}

}

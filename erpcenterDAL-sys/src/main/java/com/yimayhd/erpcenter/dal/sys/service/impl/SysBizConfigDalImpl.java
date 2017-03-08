package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.dal.sys.dao.SysBizConfigMapper;
import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;
import com.yimayhd.erpcenter.dal.sys.service.SysBizConfigDal;

public class SysBizConfigDalImpl implements SysBizConfigDal {

	@Resource
	private SysBizConfigMapper configMapper;
	
	@Override
	public List<SysBizConfig> getListByBizId(Integer bizId) {
		return configMapper.selectListByBizId(bizId);
	}

	@Override
	public Map<String, String> getConfigMapByBizId(int bizId) {
		List<SysBizConfig> list = configMapper.selectListByBizId(bizId);
		Map<String,String> map = new HashMap<String,String>();
		if(list!=null && list.size()>0){
			for(SysBizConfig config : list){
				map.put(config.getItemCode(), config.getItemValue());
			}
		}
		return map;
	}
	
	@Override
	public SysBizConfig findByItemValue(int bizId, String itemCode) {
		SysBizConfig sysConfigBean = configMapper.selectByBizIdAndCode(bizId, itemCode);
		return sysConfigBean;
	}

//	@Override
//	public boolean cnm(Integer bizId, String config) {
//		if(configMapper.cnm(bizId,config).equals("1")){return true;}
//		return false;
//	}

}

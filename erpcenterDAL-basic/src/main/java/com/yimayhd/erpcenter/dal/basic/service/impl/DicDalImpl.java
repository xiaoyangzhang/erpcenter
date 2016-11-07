package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.dal.basic.dao.DicMapper;
import com.yimayhd.erpcenter.dal.basic.dao.DicTypeMapper;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicDal;

public class DicDalImpl implements DicDal {

	@Autowired
	private DicMapper dicMapper;
	@Autowired
	private DicTypeMapper dicTypeMapper;

	@Override
	public DicInfo getById(Integer id) {
		return dicMapper.getById(id);
	}

	@Override
	public void update(DicInfo dicInfo) {
		dicMapper.update(dicInfo);
	}

	@Override
	public void add(DicInfo dicInfo) {
		dicMapper.insert(dicInfo);		
	}

	@Override
	public void delDic(Integer dicId) {
		dicMapper.delete(dicId);		
	}

	@Override
	public List<DicInfo> getListByTypeCode(String typeCode) {
		return dicMapper.getListByTypeCodeAndBizId(typeCode,0);
	}
	
	@Override
	public List<DicInfo> getListByTypeCode(String typeCode,Integer bizId) {
		/*//判断该字典类型是公有还是私有，公有则显示全部，私有则显示当前改单位的数据
		DicTypeInfo typeInfo = new DicTypeInfo();
		typeInfo.setCode(typeCode);
		List<DicTypeInfo> typeList = dicTypeMapper.getWhere(typeInfo);
		if(typeList!=null && typeList.size()>0){
			typeInfo = typeList.get(0);
			Integer shareStatus = typeInfo.getShareStatus();
			if(shareStatus==null||shareStatus.equals(1)){
				return dicMapper.getListByTypeCodeAndBizId(typeCode,0);
			}
		}*/
		return dicMapper.getListByTypeCodeAndBizId(typeCode,bizId);
	}
	
	@Override
	public String getCommProjectTypeTreeJsonStr(String typeCode,Integer bizId){
		List<DicInfo> dicList = dicMapper.getListByTypeCodeAndBizId(typeCode,bizId);
		if(dicList == null || dicList.size() == 0){
			return "";
		}
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(DicInfo info : dicList){
			map = new HashMap<String, String>();
			map.put("id", info.getCode()+"");
			map.put("pId", "0");
			map.put("name", info.getValue());
			list.add(map);
		}
		return JSON.toJSONString(list);
	}
	
	@Override
	public List<DicInfo> getListByTypeIdAndName(String type,
			String name) {		
		return dicMapper.getListByTypeIdAndName(type,0,name);
	}

	@Override
	public List<DicInfo> getListByTypeIdAndName(String type,Integer bizId,
			String name) {
		return dicMapper.getListByTypeIdAndName(type,bizId,name);
	}

	@Override
	public List<DicInfo> getListByTypeId( String type) {
		return null;
	}

	@Override
	public DicInfo getDicInfoByTypeCodeAndDicCode(String typeCode,
			String dicCode) {
		return getDicInfoByTypeCodeAndDicCode(0,typeCode,dicCode);
	}

	@Override
	public DicInfo getDicInfoByTypeCodeAndDicCode(Integer bizId,
			String typeCode, String dicCode) {
		List<DicInfo> list = dicMapper.getListByTypeCodeAndDicCode(bizId,typeCode, dicCode);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}

package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicDal;

public class DicBizImpl implements DicDal {
	@Autowired
	private DicDal dicDal;

	@Override
	public List<DicInfo> getListByTypeId(String type) {
		return dicDal.getListByTypeId(type);
	}

	@Override
	public DicInfo getById(String id) {
		return dicDal.getById(id);
	}

	@Override
	public void update(DicInfo dicInfo) {
		 dicDal.update(dicInfo);
		
	}

	@Override
	public void add(DicInfo dicInfo) {
		dicDal.add(dicInfo);
		
	}

	@Override
	public void delDic(String dicId) {
		dicDal.delDic(dicId);
		
	}

	@Override
	public List<DicInfo> getListByTypeCode(String typeCode) {
		return dicDal.getListByTypeCode(typeCode);
	}

	@Override
	public List<DicInfo> getListByTypeCode(String typeCode, Integer bizId) {
		return dicDal.getListByTypeCode(typeCode, bizId);
	}

	@Override
	public String getCommProjectTypeTreeJsonStr(String typeCode, Integer bizId) {
		return dicDal.getCommProjectTypeTreeJsonStr(typeCode, bizId);
	}

	@Override
	public List<DicInfo> getListByTypeIdAndName(String type, String name) {
		return dicDal.getListByTypeIdAndName(type, name);
	}

	@Override
	public List<DicInfo> getListByTypeIdAndName(String type, Integer bizId, String name) {
		return dicDal.getListByTypeIdAndName(type,bizId, name);
	}

	@Override
	public DicInfo getDicInfoByTypeCodeAndDicCode(String typeCode, String dicCode) {
		return dicDal.getDicInfoByTypeCodeAndDicCode(typeCode, dicCode);
	}

	@Override
	public DicInfo getDicInfoByTypeCodeAndDicCode(Integer bizId, String typeCode, String dicCode) {
		return dicDal.getDicInfoByTypeCodeAndDicCode(bizId, typeCode, dicCode);
	}
}

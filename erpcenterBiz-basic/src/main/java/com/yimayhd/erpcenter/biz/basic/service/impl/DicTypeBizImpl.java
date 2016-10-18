package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicTypeBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicTypeInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicTypeDal;


public class DicTypeBizImpl implements DicTypeBiz {
	@Autowired
	private DicTypeDal dicTypeDal;

	@Override
	public void add(DicTypeInfo dicTypeInfo) {
		dicTypeDal.add(dicTypeInfo);
	}

	@Override
	public void update(DicTypeInfo dicTypeInfo) {
		dicTypeDal.update(dicTypeInfo);
	}

	@Override
	public void delete(Integer id) {
		dicTypeDal.delete(id);
	}

	@Override
	public void delete(String id) {
		dicTypeDal.delete(id);
	}

	@Override
	public DicTypeInfo getById(String id) {
		return dicTypeDal.getById(id);
	}

	@Override
	public DicTypeInfo getById(Integer id) {
		return dicTypeDal.getById(id);
	}

	@Override
	public List<DicTypeInfo> getListByPid(Integer pid) {
		return dicTypeDal.getListByPid(pid);
	}

	@Override
	public List<DicTypeInfo> getListByPidAndName(String pid, String name) {
		
		return dicTypeDal.getListByPidAndName(pid, name);
		
	}

	@Override
	public String getDicTypeJson() {
		return dicTypeDal.getDicTypeJson();
	}

	@Override
	public List<DicTypeInfo> getAll() {
		return dicTypeDal.getAll();
	}
}

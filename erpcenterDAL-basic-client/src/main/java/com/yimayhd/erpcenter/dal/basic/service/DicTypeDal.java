package com.yimayhd.erpcenter.dal.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicTypeInfo;


public interface DicTypeDal {
	void add(DicTypeInfo dicTypeInfo);
	void update(DicTypeInfo dicTypeInfo);
	void delete(Integer id);
	void delete(String id);
	DicTypeInfo getById(String id);
	DicTypeInfo getById(Integer id);
	List<DicTypeInfo> getListByPid(Integer pid);
	List<DicTypeInfo> getListByPidAndName(String pid,String name);
	String getDicTypeJson();
	List<DicTypeInfo> getAll();
}

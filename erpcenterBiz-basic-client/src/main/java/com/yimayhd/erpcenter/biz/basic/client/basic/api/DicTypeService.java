package com.yimayhd.erpcenter.biz.basic.client.basic.api;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.client.basic.po.DicTypeInfo;



public interface DicTypeService {
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

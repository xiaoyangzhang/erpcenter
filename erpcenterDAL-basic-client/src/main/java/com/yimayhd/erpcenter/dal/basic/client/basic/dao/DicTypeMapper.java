package com.yimayhd.erpcenter.dal.basic.client.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.basic.client.basic.po.DicTypeInfo;



public interface DicTypeMapper {
	
	void insert(DicTypeInfo model);
	void update(DicTypeInfo model);
	void delete(Integer id);
	DicTypeInfo getById(Integer id);
	int getCount(DicTypeInfo model);
	List<DicTypeInfo> getAll();
	List<DicTypeInfo> getWhere(DicTypeInfo model);
	List<DicTypeInfo> getPageList(@Param("start") int start,@Param("pageSize") int pageSize);
	List<DicTypeInfo> getListByPid(@Param("pid")Integer pid);
	List<DicTypeInfo> getListByPidAndName(@Param("pid")Integer pid, @Param("name")String name);
	
}

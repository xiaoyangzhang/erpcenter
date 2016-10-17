package com.yimayhd.erpcenter.dal.basic.client.basic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.basic.client.basic.po.DicInfo;

public interface DicMapper{
	void insert(DicInfo info);
	void update(DicInfo info);
	void delete(String id);
	DicInfo getById(String id);
	int getCount(DicInfo info);
	List<DicInfo> getAll();
	List<DicInfo> getWhere(DicInfo info);
	List<DicInfo> getListByTypeId(@Param("typeId")String typeId);
	List<DicInfo> getListByTypeCodeAndBizId(@Param("typeCode")String typeCode,@Param("bizId")int bizId);
	List<DicInfo> getListByTypeIdAndName(@Param("typeId")String typeId,@Param("bizId")int bizId ,@Param("name")String name);
	List<DicInfo> getListByTypeCodeAndDicCode(@Param("bizId")int bizId,@Param("typeCode")String typeCode,@Param("dicCode")String dicCode);
}

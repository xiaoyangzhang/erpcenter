package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;

public interface SysBizConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBizConfig record);

    int insertSelective(SysBizConfig record);

    SysBizConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysBizConfig record);

    int updateByPrimaryKey(SysBizConfig record);
    
    List<SysBizConfig> selectListByBizId(@Param("bizId")Integer bizId);

	String cnm(@Param("bizId")Integer bizId, @Param("config")String config);
	
	SysBizConfig selectByBizIdAndCode(@Param("bizId")Integer bizId, @Param("itemCode")String itemCode);
}
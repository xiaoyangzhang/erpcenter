package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;


public interface SysDataRightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDataRight record);

    int insertSelective(SysDataRight record);
    
    int insertBatch(List<SysDataRight> list);

    SysDataRight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDataRight record);

    int updateByPrimaryKey(SysDataRight record);
    List<SysDataRight> getSysDataRightByUserId(Integer userId);
    List<SysDataRight> getSysDataRightByViewUserId(Integer userId);
    int deleteDataRight(SysDataRight dataRight);
    
    int deleteByViewUserId(Integer userId);
    
    
}
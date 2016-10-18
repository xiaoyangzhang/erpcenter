package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;

public interface SysBizInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBizInfo record);

    int insertSelective(SysBizInfo record);

    SysBizInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysBizInfo record);

    int updateByPrimaryKey(SysBizInfo record);

    List<SysBizInfo> getListByCode(String code);
    List<SysBizInfo> getBizListPage(PageBean page);
    int chkExistByCode(String code);

	List<SysBizInfo> getAllBizs();
    
    
}
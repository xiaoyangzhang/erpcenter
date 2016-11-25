package com.yimayhd.erpcenter.dal.sys.dao;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.MsgInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MsgInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MsgInfo record);

    int insertSelective(MsgInfo record);

    MsgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgInfo record);

    int updateByPrimaryKey(MsgInfo record);

    List<MsgInfo> selectMsgInfoListPage(@Param("page") PageBean<MsgInfo> pageBean,
                                        @Param("param") Map<String, String> parameters);

    List<MsgInfo> selectMsgInfo(@Param("param") Map<String, Object> parameters);
    
    MsgInfo selectMsgByMid(@Param("param") Map<String, Object> parameters);
    
    List<MsgInfo> selectNoticeListPage(@Param("page") PageBean<MsgInfo> pageBean,
                                       @Param("param") Map<String, String> parameters);
}
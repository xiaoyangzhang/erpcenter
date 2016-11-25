package com.yimayhd.erpcenter.dal.sys.dao;

import com.yihg.sys.po.MsgInfoDetail;
import org.apache.ibatis.annotations.Param;

public interface MsgInfoDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgInfoDetail record);

    int insertSelective(MsgInfoDetail record);

    MsgInfoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgInfoDetail record);

    int updateByPrimaryKey(MsgInfoDetail record);

    MsgInfoDetail selectMsgCountByUserId(@Param("bizId") Integer bizId,
                                         @Param("userId") Integer userId);
}
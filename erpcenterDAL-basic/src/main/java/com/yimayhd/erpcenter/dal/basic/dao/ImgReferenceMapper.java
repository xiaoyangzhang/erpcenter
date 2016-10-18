package com.yimayhd.erpcenter.dal.basic.dao;

import com.yimayhd.erpcenter.dal.basic.po.ImgReference;

public interface ImgReferenceMapper {
    int deleteByPrimaryKey(Long referenceId);

    int insert(ImgReference record);

    int insertSelective(ImgReference record);

    ImgReference selectByPrimaryKey(Long referenceId);

    int updateByPrimaryKeySelective(ImgReference record);

    int updateByPrimaryKey(ImgReference record);
}
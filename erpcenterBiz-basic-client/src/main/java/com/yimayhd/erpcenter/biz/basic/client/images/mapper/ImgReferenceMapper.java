package com.yimayhd.erpcenter.biz.basic.client.images.mapper;

import com.yimayhd.erpcenter.biz.basic.images.po.ImgReference;

public interface ImgReferenceMapper {
    int deleteByPrimaryKey(Long referenceId);

    int insert(ImgReference record);

    int insertSelective(ImgReference record);

    ImgReference selectByPrimaryKey(Long referenceId);

    int updateByPrimaryKeySelective(ImgReference record);

    int updateByPrimaryKey(ImgReference record);
}
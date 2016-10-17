package com.yimayhd.erpcenter.dal.basic.client.images.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.basic.client.images.po.ImgWatermark;


public interface ImgWatermarkMapper {
	
    int deleteByPrimaryKey(Integer watermarkId);

    int insert(ImgWatermark record);

    int insertSelective(ImgWatermark record);

    ImgWatermark selectByPrimaryKey(Integer watermarkId);

    int updateByPrimaryKeySelective(ImgWatermark record);

    int updateByPrimaryKey(ImgWatermark record);

	int deleteWatermarkByThreeId(@Param("currentUserId") Integer currentUserId,@Param("currentDealerId") Integer currentDealerId,
			@Param("currentSysId") String currentSysId);
    
	List<ImgWatermark> findImgWatermarkByConditions(ImgWatermark record);
    
}
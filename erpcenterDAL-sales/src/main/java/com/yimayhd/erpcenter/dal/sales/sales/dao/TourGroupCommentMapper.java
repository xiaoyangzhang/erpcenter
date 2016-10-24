package com.yihg.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.sales.po.TourGroupComment;

public interface TourGroupCommentMapper {
	List<TourGroupComment> getTourGroupComments(@Param("bizId")Integer bizId, @Param("groupIds")String groupIds);
	int insertTourGroupComment(@Param("bizId")Integer bizId, @Param("comment")TourGroupComment comment);
	int deleteTourGroupComment(@Param("bizId")Integer bizId, @Param("groupId")Integer groupId);
}

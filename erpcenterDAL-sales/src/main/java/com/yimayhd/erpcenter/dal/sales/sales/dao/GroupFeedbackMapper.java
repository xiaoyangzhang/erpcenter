package com.yihg.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.sales.po.GroupFeedback;
import com.yihg.sales.vo.GroupFeedbackGroupStaticsVO;
import com.yihg.sales.vo.GroupFeedbackPersonalStaticsVO;

/**
 * 当前行程  旅游服务评价
 * @author lyl
 *
 */
public interface GroupFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupFeedback record);

    int insertSelective(GroupFeedback record);

    GroupFeedback selectByPrimaryKey(Integer id);

    GroupFeedback selectByPrimaryGroupId(@Param("groupId")Integer groupId,@Param("createId")Integer createId);
    
    int updateByPrimaryKeySelective(GroupFeedback record);

    int updateByPrimaryKey(GroupFeedback record);
    
    /**
     * 根据团id和身份证号获取某个顾客对某个团的评议分及平均分
     * @param groupId
     * @param idNo
     * @return
     */
    List<GroupFeedbackPersonalStaticsVO> selectPersonalStaticsByGroupIdAndIdNo(@Param("groupId")Integer groupId,@Param("idNo")String idNo);
    
    /**
     * 根据团id统计团评分（总平均分及各项平均分）
     * @param groupId
     * @return
     */
    GroupFeedbackGroupStaticsVO selectGroupStaticsByGroupId(Integer groupId);
}
package com.yimayhd.erpcenter.facade.sales.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;
import com.yimayhd.erpcenter.facade.sales.query.*;
import com.yimayhd.erpcenter.facade.sales.result.*;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/25 17:57
 */
public interface TeamGroupFacade {



    public ToGroupListResult toGroupList(int bizId);
    /***
     * 获取团队列表数据
     * @param findTourGroupByConditionDTO
     * @return
     */
    public FindTourGroupByConditionResult findTourGroupByConditionLoadModel(FindTourGroupByConditionDTO findTourGroupByConditionDTO);

    public ToAddTeamGroupInfoResult toAddTeamGroupInfo(ToAddTeamGroupInfoDTO toAddTeamGroupInfoDTO);

    public String getDataByGroupId(int groupId);

    public ContactManListResult contactManList(int curBizId, int id);

    public ToSearchListResult toSearchList(ToSearchListDTO toSearchListDTO);

    public SaveTeamGroupInfoResult saveTeamGroupInfo(SaveTeamGroupInfoDTO saveTeamGroupInfoDTO);

    public ToEditTeamGroupInfoResult toEditTeamGroupInfo(ToEditTeamGroupInfoDTO toEditTeamGroupInfoDTO);

    public ToRequirementResult toRequirement(Integer orderId,Integer operType);

    public String copyTourGroup(CopyTourGroupDTO copyTourGroupDTO);

    public String deleteGroupOrderById(Integer orderId, Integer groupId,Integer curBizId);

    public ResultSupport saveRequireMent(SaveRequireMentDTO saveRequireMentDTO);
    public ResultSupport saveOrUpdateRequirement(TeamGroupVO vo,Integer curBizId,String userName);

    public FindTourGroupLoadFooterResult findTourGroupLoadFooter(FindTourGroupLoadFooterDTO findTourGroupLoadFooterDTO);

}

package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.FindTourGroupByConditionDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToAddTeamGroupInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToSearchListDTO;
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

    public ToAddTeamGroupInfoResult getDataByGroupId(ToAddTeamGroupInfoDTO toAddTeamGroupInfoDTO);


    public ContactManListResult contactManList(int curBizId, int id);

    public ToSearchListResult toSearchList(ToSearchListDTO toSearchListDTO);






}

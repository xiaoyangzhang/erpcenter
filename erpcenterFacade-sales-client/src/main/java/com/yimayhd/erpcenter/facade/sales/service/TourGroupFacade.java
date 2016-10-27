package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OtherInfoVO;
import com.yimayhd.erpcenter.facade.sales.query.ChangeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.result.*;
import org.springframework.ui.Model;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 10:58
 */
public interface TourGroupFacade {
    public ToChangeGroupResult toChangeGroup(int groupId);
    public ToChangeGroupResult changeGroup(ChangeGroupDTO changeGroupDTO);
    public GetPushInfoResult getPushInfo(int groupId);
    public ResultSupport updateGuide(Integer groupId, Integer driverId, Integer guideId);
    public ResultSupport updateShop(String guideName, Integer guideId, Integer shopId);
    public ResultSupport pushInfo(Integer groupId);
    public ResultSupport pushWap(Integer groupId);
    public ToChangeGroupResult toEditGroup(Integer groupId);
    public ToAddTourGroupOrderResult toAddTourGroupOrder(Integer groupId, Integer orderId,Integer state,String curUserName,Integer CurUserId,Integer bizId);
    public ToChangeGroupResult saveTourGroupOrder(TourGroup tourGroup,GroupOrder groupOrder,Integer curBizId,Integer curUserOrgId,Integer curUserId, String curUserName);
    public ResultSupport unifiedSaveOtherInfo(OtherInfoVO otherInfoVO,String curUserName,Integer CurUserId);
    public ToOtherInfoResult toOtherInfo(Integer orderId,Integer stateFinance, Integer state,Integer curBizId );
    public TogroupRequirementResult togroupRequirement(Integer orderId,Integer stateFinance, Integer state,Integer curBizId );
    public ToGroupListResult findTourGroupByCondition(	String yesOrNo, GroupOrder groupOrder,Integer curBizId );





}

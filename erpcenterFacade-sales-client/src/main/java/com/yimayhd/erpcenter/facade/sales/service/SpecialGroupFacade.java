package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.facade.sales.result.ToAddSpecialGroupResult;
import com.yimayhd.erpcenter.facade.sales.result.ToGroupListResult;

import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 11:52
 */
public interface SpecialGroupFacade {
    public ToAddSpecialGroupResult toAddSpecialGroup(Integer userId,String userName,Integer bizId);
    public ToAddSpecialGroupResult toEditSpecialGroup(Integer id,Integer operType,Integer bizId);
    public ToAddSpecialGroupResult saveSpecialGroup(SpecialGroupOrderVO vo,Integer userOrgId,Integer bizId,Integer userId,String userName);
    public ToAddSpecialGroupResult toSpecialGroupList(Integer bizId);
    public ToAddSpecialGroupResult getSpecialGroupData(GroupOrder groupOrder,Integer bizId, Set<Integer> userIdSet);
    public ToAddSpecialGroupResult getSpecialGroup( TourGroup tourGroup,Integer tid,Integer bizId, Set<Integer> userIdSet);
    public ToAddSpecialGroupResult toMergeGroup(  String ids);
    public ToAddSpecialGroupResult mergetGroup(MergeGroupOrderVO mergeGroupOrderVO,Integer bizId, Integer orgId,Integer userId,String userName);
    public ToAddSpecialGroupResult toImpNotGroupList(GroupOrder groupOrder,String idLists,Integer bizId,Set<Integer> userIdSet);
    public ToAddSpecialGroupResult insertGroup( Integer id, String code);
    public ToAddSpecialGroupResult insertGroupMany(String ids, String code);
    public ToAddSpecialGroupResult beforeInsertGroup(String ids);
    public ToAddSpecialGroupResult judgeMergeGroup(String ids);
}

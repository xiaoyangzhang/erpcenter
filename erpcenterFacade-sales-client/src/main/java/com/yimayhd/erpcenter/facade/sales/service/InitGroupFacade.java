package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SalesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.result.InitGroupResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.QualityListResult;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:54
 */
public interface InitGroupFacade {
    InitGroupResult qualityList(Integer curBizId);
    InitGroupResult initGroupList(Integer pageSize, Integer page,TourGroupVO group,String guideName,Map<String,Object> pm,Set<Integer> userIdSet,Integer bizId);
    InitGroupResult getInitGroupList(Integer groupId,Integer bizId);
    InitGroupResult saveInitGroupInfo(SalesVO salesVO,Integer bizId,Integer userId,String userName);
    InitGroupResult deleteInitGroupInfo(Integer groupId);
}


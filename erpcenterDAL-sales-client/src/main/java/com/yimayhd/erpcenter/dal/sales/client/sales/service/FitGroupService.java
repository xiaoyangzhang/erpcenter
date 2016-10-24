package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import com.yihg.sales.vo.FitGroupInfoVO;

public interface FitGroupService {
  public FitGroupInfoVO selectFitGroupInfoById(Integer groupId);
  public void  updateFitGroupInfo(FitGroupInfoVO fitGroupInfoVO,Integer userId,String userName);
}

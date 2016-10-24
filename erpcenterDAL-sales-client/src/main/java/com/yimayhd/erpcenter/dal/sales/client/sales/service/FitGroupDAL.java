package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;

public interface FitGroupDAL {
  public FitGroupInfoVO selectFitGroupInfoById(Integer groupId);
  public void  updateFitGroupInfo(FitGroupInfoVO fitGroupInfoVO,Integer userId,String userName);
}

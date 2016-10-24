package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;

public interface FitGroupDal {
  public FitGroupInfoVO selectFitGroupInfoById(Integer groupId);
  public void  updateFitGroupInfo(FitGroupInfoVO fitGroupInfoVO,Integer userId,String userName);
}

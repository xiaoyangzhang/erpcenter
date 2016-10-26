package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.text.ParseException;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;

public interface FitOrderBiz {
  public FitOrderVO selectFitOrderVOById(Integer orderId);
  public Integer  saveOrUpdateFitOrderInfo(FitOrderVO fitOrderVO,Integer userId,String userName,Integer proOperId,String proOperName ,Integer bizId,String bizCode,boolean mergerGroup)throws ParseException;
  public void mergetGroup(List<MergeGroupOrderVO> list,Integer bizId,Integer operid, String operName, String supplierCode,boolean isAgency)throws ParseException;
  
  /**
   * 查找所有未到期预留订单
   * @return
   */
  public List<GroupOrder> selectReserveOrderList();
  
  public void saveTransportInfo(FitOrderVO fitOrderVO);
  
  public void saveGuestInfo(FitOrderVO fitOrderVO);
}

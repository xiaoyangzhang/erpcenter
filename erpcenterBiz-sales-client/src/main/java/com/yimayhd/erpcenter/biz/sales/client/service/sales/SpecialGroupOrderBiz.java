package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.text.ParseException;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;


public interface SpecialGroupOrderBiz {
	public Integer saveOrUpdateSpecialOrderInfo(SpecialGroupOrderVO sgovo,Integer userId,String userName,Integer bizId) throws ParseException;
	public SpecialGroupOrderVO selectSpeciaOrderlInfoByOrderId(Integer orderId);
	public void mergetGroup(List<MergeGroupOrderVO> list,Integer bizId,Integer operid, String operName, String supplierCode)throws ParseException;
	public void mergetGroupTaobao(List<MergeGroupOrderVO> list,Integer bizId,Integer operid, String operName, String supplierCode)throws ParseException;
	public void mergetGroupResTeam(List<MergeGroupOrderVO> list,Integer bizId,Integer operid, String operName, String supplierCode)throws ParseException;
	public void mergetGroupResFit(List<MergeGroupOrderVO> list,Integer bizId,Integer operid, String operName, String supplierCode)throws ParseException;
	public Integer saveGuestListInfo(SpecialGroupOrderVO sgovo,Integer userId,String userName,Integer bizId) throws ParseException;
	public  Integer savePrice(List<GroupOrderPrice> groupOrderPrices, Integer orderId, Integer userId, String userName, Integer bizId) throws ParseException ;
}

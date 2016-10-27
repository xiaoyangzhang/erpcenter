package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.SpecialGroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;

public class SpecialGroupOrderBizImpl implements SpecialGroupOrderBiz {
	@Autowired
	private SpecialGroupOrderDal specialGroupOrderDal;

	@Override
	public Integer saveOrUpdateSpecialOrderInfo(SpecialGroupOrderVO sgovo,
			Integer userId, String userName, Integer bizId)
			throws ParseException {
		return specialGroupOrderDal.saveOrUpdateSpecialOrderInfo(sgovo, userId, userName, bizId);
	}

	@Override
	public SpecialGroupOrderVO selectSpeciaOrderlInfoByOrderId(Integer orderId) {
		return specialGroupOrderDal.selectSpeciaOrderlInfoByOrderId(orderId);
	}

	@Override
	public void mergetGroup(List<MergeGroupOrderVO> list, Integer bizId,
			Integer operid, String operName, String supplierCode)
			throws ParseException {
		specialGroupOrderDal.mergetGroup(list, bizId, operid, operName, supplierCode);
	}
	
	@Override
	public void mergetGroupTaobao(List<MergeGroupOrderVO> list, Integer bizId,
			Integer operid, String operName, String supplierCode)
			throws ParseException {
		specialGroupOrderDal.mergetGroupTaobao(list, bizId, operid, operName, supplierCode);
	}

	@Override
	public void mergetGroupResTeam(List<MergeGroupOrderVO> list, Integer bizId,
			Integer operid, String operName, String supplierCode)
			throws ParseException {
		specialGroupOrderDal.mergetGroupResTeam(list, bizId, operid, operName, supplierCode);
	}
	
	@Override
	public void mergetGroupResFit(List<MergeGroupOrderVO> list, Integer bizId,
			Integer operid, String operName, String supplierCode)
			throws ParseException {
		specialGroupOrderDal.mergetGroupResFit(list, bizId, operid, operName, supplierCode);
	}
	
	@Override
	public Integer saveGuestListInfo(SpecialGroupOrderVO sgovo,
			Integer userId, String userName, Integer bizId)
			throws ParseException {
		return specialGroupOrderDal.saveGuestListInfo(sgovo, userId, userName, bizId);
	}
}

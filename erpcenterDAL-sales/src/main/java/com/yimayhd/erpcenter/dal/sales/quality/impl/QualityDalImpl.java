package com.yimayhd.erpcenter.dal.sales.quality.impl;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.quality.service.QualityDal;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;

import java.util.List;

import javax.annotation.Resource;


public class QualityDalImpl implements QualityDal {

	@Resource
	private TourGroupMapper groupMapper;
	@Resource
	private GroupOrderMapper groupOrderMapper;
	
	@Override
	public PageBean getQualityTourGroupList(
			PageBean<QualityTourGroupVo> pageBean) {		
		List<QualityTourGroupVo> list = groupMapper.selectQualityTourGroupListPage(pageBean);		
		pageBean.setResult(fillGroupInfo(list));		
		return pageBean;
	}
	
	private List<QualityTourGroupVo> fillGroupInfo(List<QualityTourGroupVo> list){
		if(list!=null && list.size()>0){
			for(QualityTourGroupVo vo : list){
				if(vo.getGroupMode()>0){
					List<GroupOrder> orderList = groupOrderMapper.selectOrderByGroupId(vo.getGroupId());
					if(orderList!=null && orderList.size()>0){
						vo.setSupplierId(orderList.get(0).getSupplierId());
						vo.setSupplierName(orderList.get(0).getSupplierName());
						vo.setOrderId(orderList.get(0).getId());
					}
				}
			}
		}
		return list;
	}

}

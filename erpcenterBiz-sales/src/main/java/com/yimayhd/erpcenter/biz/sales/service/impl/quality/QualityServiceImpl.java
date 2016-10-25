package com.yimayhd.erpcenter.biz.sales.service.impl.quality;

import java.util.List;

import javax.annotation.Resource;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.quality.QualityBiz;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TourGroupDal;

public class QualityServiceImpl implements QualityBiz {

	@Resource
	private TourGroupDal tourGroupDal;
	@Resource
	private GroupOrderDal groupOrderDal;
	
	@Override
	public PageBean getQualityTourGroupList(
			PageBean<QualityTourGroupVo> pageBean) {
		List<QualityTourGroupVo> list = tourGroupDal.selectQualityTourGroupListPage(pageBean);
		pageBean.setResult(fillGroupInfo(list));		
		return pageBean;
	}
	
	private List<QualityTourGroupVo> fillGroupInfo(List<QualityTourGroupVo> list){
		if(list!=null && list.size()>0){
			for(QualityTourGroupVo vo : list){
				if(vo.getGroupMode()>0){
					List<GroupOrder> orderList = groupOrderDal.selectOrderByGroupId(vo.getGroupId());
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

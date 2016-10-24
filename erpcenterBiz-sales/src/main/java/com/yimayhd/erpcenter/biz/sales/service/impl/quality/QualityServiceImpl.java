package com.yimayhd.erpcenter.biz.sales.service.impl.quality;

import java.util.List;

import javax.annotation.Resource;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.quality.api.QualityService;
import com.yihg.quality.vo.QualityTourGroupVo;
import com.yihg.sales.dao.GroupOrderMapper;
import com.yihg.sales.dao.TourGroupMapper;
import com.yihg.sales.po.GroupOrder;

public class QualityServiceImpl implements QualityService {

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

package com.yimayhd.erpcenter.biz.sales.service.impl.quality;

import java.util.List;

import javax.annotation.Resource;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.quality.QualityBiz;
import com.yimayhd.erpcenter.dal.sales.client.quality.service.QualityDal;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TourGroupDal;

public class QualityBizImpl implements QualityBiz {
	@Resource
	private QualityDal qualityDal;

	@Override
	public PageBean getQualityTourGroupList(
			PageBean<QualityTourGroupVo> pageBean) {
		return qualityDal.getQualityTourGroupList(pageBean);
	}

}

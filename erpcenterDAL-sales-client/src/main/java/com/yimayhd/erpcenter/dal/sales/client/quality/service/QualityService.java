package com.yimayhd.erpcenter.dal.sales.client.quality.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupVo;


public interface QualityService {
	/**
	 * 查询质量统计列表
	 * @param pageBean
	 * @return
	 */
	PageBean getQualityTourGroupList(PageBean<QualityTourGroupVo> pageBean);
}

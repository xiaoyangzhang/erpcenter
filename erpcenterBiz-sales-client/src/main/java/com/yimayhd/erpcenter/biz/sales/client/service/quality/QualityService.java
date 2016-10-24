package com.yimayhd.erpcenter.biz.sales.client.service.quality;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.quality.vo.QualityTourGroupVo;

public interface QualityService {
	/**
	 * 查询质量统计列表
	 * @param pageBean
	 * @return
	 */
	PageBean getQualityTourGroupList(PageBean<QualityTourGroupVo> pageBean);
}

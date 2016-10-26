package com.yimayhd.erpcenter.biz.sales.client.service.quality;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupVo;

public interface QualityBiz {
	/**
	 * 查询质量统计列表
	 * @param pageBean
	 * @return
	 */
	PageBean getQualityTourGroupList(PageBean<QualityTourGroupVo> pageBean);
}

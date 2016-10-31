package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.quality.LoadQualityListDTO;
import com.yimayhd.erpcenter.facade.sales.query.quality.SaveReplyDTO;
import com.yimayhd.erpcenter.facade.sales.query.quality.SupplierCommentListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.GroupQualityInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.LoadQualityListResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.QualityListResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.SupplierCommentListResult;

/**
 * 
 * @author gaotingping
 * 2016年10月31日
 */
public interface QualityFacade {

	QualityListResult qualityList(Integer curBizId);

	LoadQualityListResult loadQualityList(LoadQualityListDTO loadQualityListDTO);

	GroupQualityInfoResult groupQualityInfo(Integer groupId);

	SupplierCommentListResult supplierCommentList(SupplierCommentListDTO supplierCommentListDTO);

	BaseStateResult updateCommentState(int id, int state);

	BaseStateResult updateTagState(String name, int state);

	BaseStateResult saveReply(SaveReplyDTO saveReplyDTO);
}

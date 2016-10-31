package com.yimayhd.erpcenter.facade.sales.service.bak;

import com.yimayhd.erpcenter.facade.sales.query.ToSKConfirmPreviewDTO;
import com.yimayhd.erpcenter.facade.sales.result.ToSKConfirmPreviewResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.PreviewFitTransferResult;

/**
 * 销售-团信息等查询打印
 * 
 * @author gaotingping
 * 2016年10月26日
 */
public interface GroupQueryPrintFacade {

	//散客确认单预览
	ToSKConfirmPreviewResult toSKConfirmPreview(ToSKConfirmPreviewDTO toSKConfirmPreviewDTO);

	//预览散客计调
	PreviewFitTransferResult previewFitTransfer(Integer groupId);

}

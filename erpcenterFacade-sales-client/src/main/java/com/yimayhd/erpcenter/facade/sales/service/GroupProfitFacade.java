package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.facade.sales.query.ProfitQueryByTourDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToAddProfitChangeDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.FitUpdateStateResult;
import com.yimayhd.erpcenter.facade.sales.result.ProfitQueryByTourResult;
import com.yimayhd.erpcenter.facade.sales.result.ToProfitQueryTableResult;

/**
 * 销售-预算利润
 * 
 * @author gaotingping
 * 2016年10月26日
 */
public interface GroupProfitFacade {

	//预算利润查询分页(按订单)(地接版)
	ToProfitQueryTableResult toProfitQueryTable(ToOrderLockTableDTO orderLockTableDTO);

	//预算利润查询分页(按团)
	ProfitQueryByTourResult toProfitQueryTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO);

	//预算利润修改
	FitUpdateStateResult toAddProfitChange(ToAddProfitChangeDTO toAddProfitChangeDTO);

}

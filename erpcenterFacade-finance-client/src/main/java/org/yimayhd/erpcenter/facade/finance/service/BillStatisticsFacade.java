
package org.yimayhd.erpcenter.facade.finance.service;

import org.yimayhd.erpcenter.facade.finance.query.InRecordDTO;
import org.yimayhd.erpcenter.facade.finance.result.InRecordResult;

public interface BillStatisticsFacade{

	/**
	 * 跳转到收款明细页面
	 */
	InRecordResult inrecord(InRecordDTO dto);
}

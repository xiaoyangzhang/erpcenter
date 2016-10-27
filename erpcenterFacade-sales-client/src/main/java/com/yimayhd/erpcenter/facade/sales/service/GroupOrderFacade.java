package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AiYouBean;
import com.yimayhd.erpcenter.facade.sales.query.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.ListResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.ToOrderLockTableResult;

/**
 * GroupOrderFacade
 *
 * @author lilin
 * @date 16/10/25
 */
public interface GroupOrderFacade {

    ListResultSupport<AiYouBean> getAiYourOrders(String code, String port, String startDate, String endDate, String groupNum, Integer bizId);
}

package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.AiYouBean;
import com.yimayhd.erpcenter.facade.sales.result.ListResultSupport;

/**
 * GroupOrderFacade
 *
 * @author lilin
 * @date 16/10/25
 */
public interface GroupOrderFacade {

    ListResultSupport<AiYouBean> getAiYourOrders(String code, String port, String startDate, String endDate, String groupNum, Integer bizId);
}

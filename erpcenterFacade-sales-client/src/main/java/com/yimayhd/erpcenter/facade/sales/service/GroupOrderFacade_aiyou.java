package com.yimayhd.erpcenter.facade.sales.service;

import java.text.ParseException;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.AiYouBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.ListResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockListResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToOrderLockTableResult;

/**
 * GroupOrderFacade
 *
 *  爱游的请以aiyou为关键词命名
 *  by gtp 10.27
 *  
 * @author lilin
 * @date 16/10/25
 */
public interface GroupOrderFacade_aiyou {

    ListResultSupport<AiYouBean> getAiYourOrders(String code, String port, String startDate, String endDate, String groupNum, Integer bizId);
    
    Integer saveAiYouDataToGroupOrder(List<GroupOrder> groupOrderList);
}

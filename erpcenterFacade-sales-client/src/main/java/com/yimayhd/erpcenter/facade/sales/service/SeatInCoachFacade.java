package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.facade.sales.result.SaveSeatInCoachResult;
import com.yimayhd.erpcenter.facade.sales.result.ToAddSpecialGroupResult;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:15
 */
public interface SeatInCoachFacade {
    public SaveSeatInCoachResult saveAndEditSeatInCoach(String transport);
    public SaveSeatInCoachResult saveSeatInCoach(GroupOrderTransport groupOrderTransport);
    public SaveSeatInCoachResult editSeatInCoach(Integer id);
    public SaveSeatInCoachResult updateSeatInCoach(GroupOrderTransport groupOrderTransport);
    public SaveSeatInCoachResult deleteSeatInCoachById(Integer id);
    public SaveSeatInCoachResult batchInput(List<String> userArray);

}

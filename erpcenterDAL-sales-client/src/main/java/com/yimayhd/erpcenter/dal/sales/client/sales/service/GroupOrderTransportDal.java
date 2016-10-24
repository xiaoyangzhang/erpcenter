package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.Transport;

/**
 * 销售订单-接送信息
 * @author qindz
 *
 */
public interface GroupOrderTransportDal {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupOrderTransport record);

    int insertSelective(GroupOrderTransport record);

    GroupOrderTransport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupOrderTransport record);

    int updateByPrimaryKey(GroupOrderTransport record);
    
    List<GroupOrderTransport> selectByOrderId(Integer orderId);
    
    List<GroupOrderTransport> selectByCustomerCertificateNum(String certificateNum,Integer groupId);
    
    int saveAndEditSeatInCoach(Transport requirement);
    
    /**
     * 根据团id查询该团下所有的接送信息
     * @param groupId
     * @return
     */
    List<GroupOrderTransport> selectByGroupId(Integer groupId) ;
}

package com.yihg.sales.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.sales.po.GroupOrderTransport;

public interface GroupOrderTransportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupOrderTransport record);

    int insertSelective(GroupOrderTransport record);

    GroupOrderTransport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupOrderTransport record);

    int updateByPrimaryKey(GroupOrderTransport record);
    
    List<GroupOrderTransport> selectByOrderId(Integer orderId);
    
    List<GroupOrderTransport> selectByCustomerCertificateNum(@Param("certificateNum")String certificateNum,@Param("groupId")Integer groupId);
    
    List<GroupOrderTransport> selectByGroupId(@Param("groupId")Integer groupId) ;
   // String getTransportInfoByOrderId(@Param("orderIds")String orderIds);
    /**
     * 传入参数，查询所有客人的航班落地时间分布
     * @param parameter
     * @return
     */
    List<Map<String,Object>> selectGuestAirTimeList(@Param("parameter")Map parameter);
    
	List<Map<String,Object>> selectTransportForOrders(@Param("page")PageBean pageBean, @Param("orderIds")String orderIds);
}

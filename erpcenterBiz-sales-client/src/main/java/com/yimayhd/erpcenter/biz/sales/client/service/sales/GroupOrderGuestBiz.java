package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;

/**
 * 销售订单-客人
 * @author qindz
 *
 */
public interface GroupOrderGuestBiz {
	
    int deleteByPrimaryKey(Integer id);

    int insert(GroupOrderGuest record);

    int insertSelective(GroupOrderGuest record);

    GroupOrderGuest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupOrderGuest record);

    int updateByPrimaryKey(GroupOrderGuest record);
    
    /**
     * 根据订单id查询当前订单下男女总和
     * @param orderId
     * @return
     */
    GroupOrderGuest selectGenderSum(Integer orderId);
    
    /**
     * 根据订单id查询当前订单下客人的详细信息
     * @param orderId
     * @return
     */
    List<GroupOrderGuest> selectByOrderId(Integer orderId);
    
    /**
     * 根据订单id统计当前订单下客人数量
     * @param orderId
     * @return
     */
    Integer selectGuestCountByOrderId(Integer id);
    /**
     * 查询当前订单下的成人数
     * @param id
     * @return
     */
    Integer selectNumAdultByOrderID(Integer id) ;
    /**
     * 查询当前订单下的小孩数
     * @param id
     * @return
     */
    Integer selectNumChildByOrderID(Integer id) ;
    /**
     * 查询当前订单下的全陪数
     * @param id
     * @return
     */
    Integer selectNumGuideByOrderID(Integer id) ;
    
    List<GroupOrderGuest> getGroupOrderGuests(Map parameters);
   
    /**
     * 根据身份证号码和订单号查询客人所属的订单id
     * @param guestCertificateNum
     * @return
     */
    List<GroupOrderGuest> getGuestByGuestCertificateNum(String guestCertificateNum,Integer orderId);
    
    /**
     * 根据团id和客人类型查询客人信息
     * @param groupId
     * @param guestType
     * @return
     */
    List<GroupOrderGuest> getGuestByGroupIdAndType(Integer groupId,Integer guestType) ;
    /**
     * 根绝团id和是否领队查询客人信息
     * @param groupId
     * @param isLeader
     * @return
     */
    List<GroupOrderGuest> getGuestByGroupIdAndIsLeader(Integer groupId,Integer isLeader) ;
    
}

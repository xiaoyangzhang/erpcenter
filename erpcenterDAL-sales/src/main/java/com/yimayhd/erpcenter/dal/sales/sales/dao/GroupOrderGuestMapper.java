package com.yihg.sales.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.sales.po.GroupOrder;
import com.yihg.sales.po.GroupOrderGuest;
import com.yihg.sales.vo.SaleOperatorVo;

public interface GroupOrderGuestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupOrderGuest record);

    int insertSelective(GroupOrderGuest record);

    GroupOrderGuest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupOrderGuest record);

    int updateByPrimaryKey(GroupOrderGuest record);
    
    List<GroupOrderGuest> selectByOrderId(Integer orderId);

    GroupOrderGuest selectGenderSum(Integer orderId);
    
    Integer selectGuestCountByOrderId(Integer id);

    Integer selectNumAdultByOrderID(Integer id) ;
   
    Integer selectNumChildByOrderID(Integer id) ;
    
    Integer selectNumGuideByOrderID(Integer id) ;
    
    List<GroupOrderGuest> getGroupOrderGuestList(@Param("parameter")Map parameter);
    /**
     * 传入参数，查询所有客人的性别信息
     * @param parameter
     * @return
     */
    List<GroupOrderGuest> selectGroupOrderGuestList(@Param("parameter")Map parameter);
    /**
     * 传入参数，查询所有客人的年龄段分布
     * @param parameter
     * @return
     */
    
    List<Map<String,Object>> selectGuestAgeList(@Param("parameter")Map parameter);
    /**
     * 传入参数，查询所有客人的客源地分布
     * @param parameter
     * @return
     */
    List<Map<String,Object>> selectGuestSourceList(@Param("parameter")Map parameter);

    List<GroupOrderGuest> getGuestByGuestCertificateNum(@Param("guestCertificateNum")String guestCertificateNum,@Param("orderId")Integer orderId);
    
    List<GroupOrderGuest> getGuestByGroupIdAndType(@Param("groupId")Integer groupId,@Param("guestType")Integer guestType) ;
    
    List<GroupOrderGuest> getGuestByGroupIdAndIsLeader(@Param("groupId")Integer groupId,@Param("isLeader")Integer isLeader) ;
    String getGuestInfoByOrderId(@Param("orderId")Integer orderId);
    
    
	List<Map<String,Object>> selectGuestForOrders(@Param("page")PageBean pageBean, @Param("orderIds")String orderIds);
	
	List<GroupOrderGuest> selectOrderIdsByNameOrMobile(@Param("page")PageBean pageBean);
}